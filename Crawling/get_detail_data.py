#코디 상하의 정보 크롤링

import csv
from lib2to3.pgen2 import driver
import shutil
import time
import urllib.request
from urllib.parse import quote_plus

import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.alert import Alert
from selenium.webdriver.chrome.service import Service


def musinsa_crawling(item_num):
    url = 'https://www.musinsa.com/app/styles/views/'
    url += str(item_num)
    url += '?use_yn_360=&style_type=&brand=&model=&tag_no=&max_rt=&min_rt=&display_cnt=60&list_kind=big&sort=date'

    print(url)

    chrome_options = webdriver.ChromeOptions()
    chrome_options.add_argument("--incognito")
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--no-sandbox")
    chrome_options.add_argument("--disable-setuid-sandbox")
    chrome_options.add_argument("--disable-dev-shm-usage")
    chrome_options.add_experimental_option('excludeSwitches', ['enable-logging'])

    service = Service('/Users/songjongho/종호/Crawling/chromedriver')

    driver = webdriver.Chrome(service=service, options=chrome_options)
    driver.get(url)

    time.sleep(3)

    try:
        #alert 창 객체 얻기
        result = Alert(driver)
        result.accept()
        result.dismiss()

    except:
        page_string = driver.page_source
        soup = BeautifulSoup(page_string, features="html.parser")

        item_list = soup.findAll('div', {'class': 'swiper-slide style_contents_size swiper-slide-active'})
        for item in item_list:
            item_img = ""
            item_name = ""
            item_brand = ""
            item_link = ""
            codi_num = 1

            temp = item.find('img')['src']

            item_img = 'https:' + temp
            print(item_img)

            item_name = item.find('a', {'class': 'brand_item'}).get_text()
            print(item_name)

            item_brand = item.find('a', {'class': 'brand'}).get_text()
            print(item_brand)

            item_link = item.find('a', {'class': 'brand_item'})['href']
            item_link = 'https://store.musinsa.com' + item_link
            print(item_link)

            print(codi_num)

            Detail_list.append((item_num, item_img, item_name, item_brand, item_link, codi_num))
            codi_num += 1

        item_list = soup.findAll('div', {'class': 'swiper-slide style_contents_size swiper-slide-next'})
        for item in item_list:
            item_img = ""
            item_name = ""
            item_brand = ""
            item_link = ""

            temp = item.find('img')['src']
            item_img = 'https:' + temp
            print(item_img)

            item_name = item.find('a', {'class': 'brand_item'}).get_text()
            print(item_name)

            item_brand = item.find('a', {'class': 'brand'}).get_text()
            print(item_brand)

            item_link = item.find('a', {'class': 'brand_item'})['href']
            item_link = 'https://store.musinsa.com' + item_link
            print(item_link)

            print(codi_num)

            Detail_list.append((item_num, item_img, item_name, item_brand, item_link, codi_num))
            codi_num += 1

        item_list = soup.findAll('div', {'class': 'swiper-slide style_contents_size'})
        for item in item_list:
            item_img = ""
            item_name = ""
            item_brand = ""
            item_link = ""

            temp = item.find('img')['src']
            item_img = 'https:' + temp
            print(item_img)

            item_name = item.find('a', {'class': 'brand_item'}).get_text()
            print(item_name)

            item_brand = item.find('a', {'class': 'brand'}).get_text()
            print(item_brand)

            item_link = item.find('a', {'class': 'brand_item'})['href']
            item_link = 'https://store.musinsa.com' + item_link
            print(item_link)

            print(codi_num)

            Detail_list.append((item_num, item_img, item_name, item_brand, item_link, codi_num))
            codi_num += 1
    finally:
        driver.quit()
        #driver.close()

Item_list = []
Detail_list = []

#정보 불러오기
f = open('codi_data.csv', 'r')
rdr = csv.reader(f)

for line in rdr:
    Item_list.append(line)
f.close()

#데이터 수집
for i in range(4679, 4680):
    print(Item_list[i+1][2])
    musinsa_crawling(Item_list[i+1][2])


# 이미지 저장
for i in range(len(Detail_list)):
    image_url = Detail_list[i][1]
    resp = requests.get(image_url, stream=True)
    local_file = open('/Users/songjongho/종호/detail_img_data/' + '코디' + Detail_list[i][0] + '_' + str(Detail_list[i][5]) + '.jpg', 'wb')
    resp.raw.decode_content = True
    shutil.copyfileobj(resp.raw, local_file)
    del resp

#데이터 저장
#open 'r' 로 읽었을 때 아직 없는 파일인 경우 'w' 로 open, 이미 있는 파일인 경우 'a'로 open
try :
    f = open('/Users/songjongho/종호/Crawling/detail_data.csv', 'r', encoding='utf-8-sig')
except FileNotFoundError:
    f = open('/Users/songjongho/종호/Crawling/detail_data.csv', 'w', encoding='utf-8-sig')
    f.write('번호, 제품이미지, 제품명, 브랜드, 제품링크, 코디번호\n')

else:
    f = open('/Users/songjongho/종호/Crawling/detail_data.csv', 'a', encoding='utf-8-sig')



#f = open('detail_data.csv', 'a', encoding='utf-8')
#f = open('/Users/songjongho/종호/Crawling/detail_data.csv', 'a', encoding='utf-8-sig')
#f.write('번호, 제품이미지, 제품명, 브랜드, 제품링크, 코디번호\n')

for i in range(len(Detail_list)):
    f.write(Detail_list[i][0] + ',' + Detail_list[i][1] + ',' + Detail_list[i][2] + ',' + Detail_list[i][3] + ',' + Detail_list[i][4] + ',' + str(Detail_list[i][5]) + '\n')

f.close()
