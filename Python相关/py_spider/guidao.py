import requests
import re
from lxml import etree

headers={
	'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0'
}

urls=['https://alpha.wallhaven.cc/search?q=id%3A24972&page={}'.format(str(i)) for i in range (1,9)]
for url in urls:	
	print(url)
	wb_data = requests.get(url,headers=headers)
	selector = etree.HTML(wb_data.text)
	links = selector.xpath('//li/figure/a/@href')
	list=[]
	for link in links:
		wb_data = requests.get(link,headers=headers)
		selector = etree.HTML(wb_data.text)
		img='https:'+selector.xpath('//img[@id="wallpaper"]/@src')[0]
		print(img)
		list.append(img)
		
	for item in list:
		data = requests.get(item,headers=headers)
		fp=open(item.split('?')[0][-20:],'wb')
		fp.write(data.content)
		fp.close()