#코디샵 크롤링

import shutil
import time
import urllib.request
from urllib.parse import quote_plus

import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.alert import Alert
from selenium.webdriver.chrome.service import Service

def musinsa_crawling(page_num):
    main_url = 'https://store.musinsa.com/app/styles/lists?use_yn_360=&style_type=&brand=&model=&tag_no=&max_rt=&min_rt=&display_cnt=60&list_kind=big&sort=date&page='
    url = main_url + str(page_num)
  
    print(url)
  
    service = Service('/Users/songjongho/종호/Crawling/chromedriver')
    driver = webdriver.Chrome(service=service)
    driver.get(url)
    
    time.sleep(3)
    #크롬창에서 우측 하단에 "남성" 직접 클릭해줘야함
    mensinsa = driver.find_element_by_class_name('mensinsa')
    mensinsa.click()

    page_string = driver.page_source
    soup = BeautifulSoup(page_string, features="html.parser")
    soup.find('dody', {'class': 'mensinsa'})
    item_list = soup.findAll('li', {'class': 'style-list-item'})
    for item in item_list:
        item_img = ""
        item_category = ""
        item_num = ""
        item_link = ""

        temp = item.find('img', {'class': 'style-list-thumbnail__img'})['src']
        item_img = 'https:' + temp
        print(item_img)

        item_category = item.find('span', {'class': 'style-list-information__text'}).get_text()
        print(item_category)

        item_num = item.find('a', {'class': 'style-list-item__link'})['onclick']
        item_num = item_num[8:-3]
        print(item_num)

        item_link += 'https://www.musinsa.com/app/styles/views/'
        item_link += str(item_num)
        item_link += '?use_yn_360=&style_type=&brand=&model=&tag_no=&max_rt=&min_rt=&display_cnt=60&list_kind=big&sort=date'
        print(item_link)

        Item_list.append((item_img, item_category, item_num, item_link))

    driver.close()


Item_list = []

#데이터 수집(page 1~9 , 32~51, 68 ~ 90 , 107 ~127 , 148 ~157, )
for i in range(148, 157):
    musinsa_crawling(i)

# 이미지 저장
for i in range(len(Item_list)):
    image_url = Item_list[i][0]
    resp = requests.get(image_url, stream=True)
    local_file = open('/Users/songjongho/종호/img_data/\\' + '코디' + Item_list[i][2] + '.jpg', 'wb')
    resp.raw.decode_content = True
    shutil.copyfileobj(resp.raw, local_file)
    del resp

#open 'r' 로 읽었을 때 아직 없는 파일인 경우 'w' 로 open, 이미 있는 파일인 경우 'a'로 open
try :
    f = open('codi_data.csv','r')
except FileNotFoundError:
    f = open('codi_data.csv','w')
    f.write('이미지, 카테고리, 번호, 링크\n')
else:
    f = open('codi_data.csv','a')



#데이터 저장
for i in range(len(Item_list)):
    f.write(Item_list[i][0] + ',' + Item_list[i][1] + ',' + Item_list[i][2] + ',' + Item_list[i][3] + '\n')

f.close()