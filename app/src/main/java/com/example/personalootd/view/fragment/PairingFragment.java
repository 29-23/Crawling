package com.example.personalootd.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personalootd.R;
import com.example.personalootd.view.RecommendItem;
import com.example.personalootd.view.activity.MainActivity;
import com.example.personalootd.view.adapter.RecommendAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// PicturFragment에서 전달받은 의류와 어울리는 코디 추천하는 곳
public class PairingFragment extends Fragment implements View.OnClickListener{

    MainActivity mainActivity;
    SharedPreferences preferences;

    // response data
    Ootd_find data;

    //백 버튼
    private ImageView backBtn;

    // Picture Fragment에서 넘겨 받는 사진 이미지뷰
    // image path는 mainActivity.imgPath 사용하기!!
    private ImageView imageView;
    private TextView recommendText;

    private int[] recommendItemNum = new int[4];

    // 퍼스널컬러 적합도 분석 결과 퍼센티지
    private TextView  springPercentage;
    private TextView  summerPercentage;
    private TextView  autumnPercentage;
    private TextView  winterPercentage;

    // 프로그레스바
    private ProgressBar springProgressBar;
    private ProgressBar summerProgressBar;
    private ProgressBar autumnProgressBar;
    private ProgressBar winterProgressBar;

    // 추천 의류 리사이클러뷰
    private RecyclerView recommendRecyclerView;
    private RecommendAdapter recommendAdapter;
    private List<RecommendItem> itemList;

    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    // 사용자 퍼스널컬러
    private String userColor;

    public PairingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity =(MainActivity)getActivity();
    }

    public static PairingFragment newInstance() {
        PairingFragment fragment = new PairingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_pairing, container, false);

        backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        imageView = view.findViewById(R.id.cloth_img);
        Glide.with(view)
                .load(mainActivity.imgPath)
                .into(imageView);

        recommendText = view.findViewById(R.id.recommend_text);

        springPercentage = view.findViewById(R.id.spring_percentage);
        summerPercentage = view.findViewById(R.id.summer_percentage);
        autumnPercentage = view.findViewById(R.id.autumn_percentage);
        winterPercentage = view.findViewById(R.id.winter_percentage);

        springProgressBar = view.findViewById(R.id.spring_progressbar);
        summerProgressBar = view.findViewById(R.id.summer_progressbar);
        autumnProgressBar = view.findViewById(R.id.autumn_progressbar);
        winterProgressBar = view.findViewById(R.id.winter_progressbar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendRecyclerView = view.findViewById(R.id.recommend_cloths_recyclerview);

        List<String> numList = new ArrayList<>();

        // user_color
        preferences = getActivity().getSharedPreferences("UserInfo", MODE_PRIVATE);
        String inputText = preferences.getString("userColor","");
        RequestBody user_color = RequestBody.create(MediaType.parse("text/plain"), inputText);

        // uploadImage
        File file = new File(mainActivity.imgPath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), fileBody);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.request(filePart, user_color).enqueue(new Callback<Ootd_find>() {
            @Override
            public void onResponse(Call<Ootd_find> call, Response<Ootd_find> response) {
                if (response.isSuccessful()) {
                    Log.d("helloworld", "local POST 성공");
                    data = response.body();
                    numList.add(data.getNum()[0]);
                    numList.add(data.getNum()[1]);
                    numList.add(data.getNum()[2]);
                    Log.d("helloworld", data.getNum()[0]);
                    initRecyclerView(numList);
                }
            }

            @Override
            public void onFailure(Call<Ootd_find> call, Throwable t) {
                Log.d("helloworld", "10.0.2.2 실패");
                Log.e("helloworld", String.valueOf(t));
            }
        });
        // initRecyclerView(numList);
    }

    private void initRecyclerView(List<String> numList) {
        itemList = new ArrayList<>();
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("clothes/bottom");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    if(numList.size() == 0) break;
                    RecommendItem recommendItem = userSnapshot.getValue(RecommendItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    for (int i=0; i < numList.size(); i++){
                        String itemNum = numList.get(i);
                        if (itemNum.equals(recommendItem.getNum())){
                            itemList.add(recommendItem); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                            numList.remove(i);
                        }
                    }
                }
                recommendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        setPercentage();

        recommendAdapter = new RecommendAdapter(itemList, R.layout.item_recommend);
        recommendRecyclerView.setAdapter(recommendAdapter);
        recommendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recommendAdapter.notifyDataSetChanged() ;
    }

    private void setPercentage() {

        // spring, summer autumn, winter 순서
        int [] percent = new int [4];
        String[] text = new String [4];

        percent[0] = Integer.parseInt(data.getSpring());
        percent[1] = Integer.parseInt(data.getSummer());
        percent[2] = Integer.parseInt(data.getAutumn());
        percent[3] = Integer.parseInt(data.getWinter());

        text[0] = percent[0]+"%";
        text[1] = percent[1]+"%";
        text[2] = percent[2]+"%";
        text[3] = percent[3]+"%";

        springPercentage.setText(text[0]);
        summerPercentage.setText(text[1]);
        autumnPercentage.setText(text[2]);
        winterPercentage.setText(text[3]);

        springProgressBar.setProgress(percent[0]);
        summerProgressBar.setProgress(percent[1]);
        autumnProgressBar.setProgress(percent[2]);
        winterProgressBar.setProgress(percent[3]);


        int max = 0, maxIndex = 0;
        for (int i = 0; i < percent.length; i++) {
            if (percent[i] > max) {
                max = percent[i];
                maxIndex = i;
            }
        }
        switch(maxIndex) {
            case 0:
                recommendText.setText("이 옷은 봄 웜톤에 가장 잘어울립니다.");
            case 1:
                recommendText.setText("이 옷은 여름 쿨톤에 가장 잘어울립니다.");
                break;
            case 2:
                recommendText.setText("이 옷은 가을 웜톤에 가장 잘어울립니다.");
                break;
            case 3:
                recommendText.setText("이 옷은 겨울 쿨톤에 가장 잘어울립니다.");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.back_btn:
            {
                // go to Picture Fragment
                mainActivity.goPictureFr();
                break;
            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
        mainActivity =null;
    }
}