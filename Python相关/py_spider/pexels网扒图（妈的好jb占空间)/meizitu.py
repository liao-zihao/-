import requests
from bs4 import BeautifulSoup
import json
import re
from lxml import etree

headers={
	'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0'
}
urls=['http://www.pexels.com/search/beauty?page={}'.format(str(i)) for i in range (2,11)]
for url in urls:	
	wb_data = requests.get(url,headers=headers)
	selector = etree.HTML(wb_data.text)
	imgs = selector.xpath('//article/a[3]/@href')
	list=[]
	for img in imgs:
		photo = img
		photo=re.sub('\?(.*)','',photo)
		print(photo)
		list.append(photo)
		
	for item in list:
		data = requests.get(item,headers=headers)
		fp=open(item.split('?')[0][-10:],'wb')
		fp.write(data.content)
		fp.close()