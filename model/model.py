from tensorflow import keras
from keras.models import load_model
from PIL import Image, ImageOps
import numpy as np

#keras_model.h5가 있는 경로로 모델 load
model = load_model('/content/gdrive/My Drive/Search_clothes/converted_keras/keras_model.h5')

data = np.ndarray(shape=(1, 224, 224, 3), dtype=np.float32)
#입력할 이미지 경로 입력
image = Image.open('/content/gdrive/My Drive/Search_clothes/코디14807_1.jpg')
#이미지 사이즈 변환
size = (224, 224)
image = ImageOps.fit(image, size, Image.ANTIALIAS)
#image를 array로 변환
image_array = np.asarray(image)

# Normalize the image(정규화)
normalized_image_array = (image_array.astype(np.float32) / 127.0) - 1
# Load the image into the array
data[0] = normalized_image_array

#모델에 데이터 넣어서 예측
prediction = model.predict(data)
#print(model.summary())
# 0:top, 1:top_short, 2: bottom, 3: bottom_short (긴팔, 반팔, 긴바지, 반바지)
print(np.argmax(prediction))
