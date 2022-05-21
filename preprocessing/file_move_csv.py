import glob
import shutil as sh
import csv

from numpy import product

#csv파일 읽어서 폴더에 섞여있는 이미지들 반팔,긴팔,반바지,긴바지 폴더로 이동시키기

#긴팔 상의 폴더까지의 경로
top_dir ='/Users/songjongho/종호/detail_img_data_3/top'
#반팔 폴더까지의 경로
top_short_dir ='/Users/songjongho/종호/detail_img_data_3/top_short'
#하의 폴더까지의 경로
bottom_dir ='/Users/songjongho/종호/detail_img_data_3/bottom'
#반바지 폴더까지의 경로
bottom_short_dir ='/Users/songjongho/종호/detail_img_data_3/bottom_short'
#이미지 파일들 전체 files에 넣기
files = glob.glob('/Users/songjongho/종호/detail_img_data_3/*.jpg')

#--------------------
Detail_list = []

#csv파일 읽기
f=open('/Users/songjongho/종호/Crawling/detail_data.csv','r')
detail_data = csv.reader(f)
for line in detail_data:
    Detail_list.append(line)
f.close()


# [행][] : i는 1 부터 시작해야함
for i in range(len(Detail_list)):
    #[행][열] -> 열 번호는 0~5 : 번호, 제품이미지(링크), 제품명, 브랜드, 제품링크, 코디번호
    product_name = Detail_list[i][2]        # 제품명
    split_product_name=product_name.split() # 제품명 쪼개기

    #반팔 분류
    for j in range(len(split_product_name)):
        if '반팔' in split_product_name[j] or split_product_name[j] == '티' :
            print(split_product_name[j])
            filename = '코디' + Detail_list[i][0] + '_' + Detail_list[i][5] + '.jpg' # 파일 이름
            for file in files:
                name = file.split('/')[-1]
                if(name == filename):
                    try:
                        sh.move(file ,top_short_dir + '/' + name)
                    except FileNotFoundError:
                        pass
            #print('반팔 분류 성공!')
                
    #긴팔 긴바지 반바지 분류 
    for j in range(len(split_product_name)):
        if('티셔츠' in split_product_name[j]  or '셔츠' in split_product_name[j] or split_product_name[j] == '후드티'):
            print(split_product_name[j])
            filename = '코디' + Detail_list[i][0] + '_' + Detail_list[i][5] + '.jpg' # 파일 이름
            for file in files:
                name = file.split('/')[-1]
                if(name == filename):
                    try:
                        sh.move(file ,top_dir + '/' + name)
                    except FileNotFoundError:
                        print('긴팔'+name)

        if(split_product_name[j] == '바지' or  '팬츠' in split_product_name[j] or '슬랙스' in split_product_name[j] or 'pants' in split_product_name[j] or 'PANTS' in split_product_name[j]):
            print(split_product_name[j])
            filename = '코디' + Detail_list[i][0] + '_' + Detail_list[i][5] + '.jpg' # 파일 이름
            for file in files:
                name = file.split('/')[-1]
                if(name == filename):
                    try:
                        sh.move(file ,bottom_dir + '/' + name)
                    except FileNotFoundError:
                        print('긴바지'+ name)
        if('쇼츠' in split_product_name[j] or '숏츠' in split_product_name[j]  or split_product_name[j] == '반바지' or 'shorts' in split_product_name[j] or '숏팬츠' in split_product_name[j]   ):
            print(split_product_name[j])        
            filename = '코디' + Detail_list[i][0] + '_' + Detail_list[i][5] + '.jpg' # 파일 이름
            for file in files:
                name = file.split('/')[-1]
                if(name == filename):                    
                    try:
                        sh.move(file ,bottom_short_dir + '/' + name)
                    except FileNotFoundError:
                        print('반바지'+name)
print('긴팔, 바지, 반바지 분류 끝!')
