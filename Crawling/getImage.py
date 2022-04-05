from urllib.request import urlopen
from urllib.parse import quote_plus
from bs4 import BeautifulSoup
from selenium import webdriver
import time
import requests
import shutil

#크롤링 url 설정(해시태그 '코디' 검색 시 결과화면)
baseUrl = 'https://www.instagram.com/explore/tags/'
searchUrl = '코디'
url = baseUrl + quote_plus(searchUrl)

#웹 드라이버로 url에 접근
driver = webdriver.Chrome()
driver.get(url)

time.sleep(3)

html = driver.page_source
soup = BeautifulSoup(html)

#데이터 수집
img_list = []

for i in range(0, 500):
    insta = soup.select('.v1Nh3.kIKUG._bz0w')

    for i in insta:
        print('https://www.instagram.com' + i.a['href'])
        imgUrl = i.select_one('.KL4Bh').img['src']
        img_list.append(imgUrl)
        img_list = list(set(img_list))
        html = driver.page_source
        soup = BeautifulSoup(html)
        insta = soup.select('.v1Nh3.kIKUG._bz0w')

    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    time.sleep(2)

n = 0

#이미지 저장
for i in range(0, 10000):
    image_url = img_list[n]
    resp = requests.get(image_url, stream=True)
    local_file = open('./img/' + searchUrl + str(n) + '.jpg', 'wb')
    resp.raw.decode_content = True
    shutil.copyfileobj(resp.raw, local_file)
    n += 1
    del resp

driver.close()