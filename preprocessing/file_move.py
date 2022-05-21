import glob
import shutil as sh


#상의 폴더까지의 경로
top_dir ='/Users/songjongho/종호/detail_img_data_2/top'
#하의 폴더까지의 경로
bottom_dir ='/Users/songjongho/종호/detail_img_data_2/bottom'
#해당 폴더에 있는 모든 jpg파일들 files에 넣기
files = glob.glob('/Users/songjongho/종호/detail_img_data_3/*.jpg')
de_dir = '/Users/songjongho/종호/detail_img_data_3/delete'

#파일을 다른 폴더로 이동
# _1, _2는 top폴더로 이동    
# _3은 bottom 폴더로 이동 ###
for file in files:
    # 파일 이름 코디 1234_1.jpg
    name = file.split('/')[-1]
    # _ 를 구분자로 나누기 1.jpg
    nameChange_1 = name.split('_')[-1] 
    # . 을 구분자로 나누기 1
    nameChange_2 = nameChange_1.split('.')[-2]

    if(int(nameChange_2) > 3 ):
        sh.move(file ,de_dir + '/' + name)
    
    # if(int(nameChange_2) == 3):
    #     sh.move(file ,bottom_dir + '/' + name)
print('file move success.')


# for f in file_1:
#   img = Image.open(f)
#   img_resize = img.resize((100,100),Image.NEAREST)
#   title, ext = os.path.splitext(f)
#   img_resize.save(title+'_resize'+ ext)