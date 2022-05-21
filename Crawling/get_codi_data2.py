#브랜드스냅 크롤링

import shutil
import time
import urllib.request
from urllib.parse import quote_plus

import requests
from bs4 import BeautifulSoup
from selenium import webdriver

def musinsa_crawling(page_num):
    main_url = 'https://www.musinsa.com/mz/brandsnap?_m=&gender=&p='
    url = main_url + str(page_num)

    print(url)

    driver = webdriver.Chrome()
    driver.get(url)

    time.sleep(3)

    page_string = driver.page_source
    soup = BeautifulSoup(page_string, features="html.parser")

    item_list = soup.findAll('div', {'class': 'articleImg'})
    for item in item_list:
        item_img = ""
        item_num = ""
        info = ""


        # info = item.find('div', {'class': 'articleImg'})
        # print(info)
        # print(' ')

        item_img = item.find('img')['src']
        print(item_img)

        item_num = item.find('a')['href']
        item_num = str(item_num[19:25])
        print(item_num)

        Item_list.append((item_img, item_num))

    driver.close()


Item_list = []

#데이터 수집
for i in range(1, 300):
    musinsa_crawling(i)

# 이미지 저장
for i in range(len(Item_list)):
    image_url = Item_list[i][0]
    resp = requests.get(image_url, stream=True)
    local_file = open('D:\Sooo\img_data2\\' + '코디' + Item_list[i][1] + '.jpg', 'wb')
    resp.raw.decode_content = True
    shutil.copyfileobj(resp.raw, local_file)
    del resp

f = open('codi_data2.csv', 'w')
f.write('이미지, 번호\n')

#데이터 저장
for i in range(len(Item_list)):
    f.write(Item_list[i][0] + ',' + Item_list[i][1] + '\n')

f.close()