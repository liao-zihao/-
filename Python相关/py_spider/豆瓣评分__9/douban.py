from lxml import etree
import requests
import csv

fp = open('duobanbook.csv','wt',newline='',encoding='utf-8')
writer = csv.writer(fp)
writer.writerow(('tag','name','url','author','publisher','date','price','rate','comment'))

headers={
	'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0'
}

wb_data=requests.get('https://book.douban.com/tag/?view=type',headers=headers)
selector = etree.HTML(wb_data.text)
tags = selector.xpath('//td/a/@href')
del tags[0]
tag_urls=[]
for tag in tags:
	#print('https://douban.com/'+tag)
	tag_urls.append('https://book.douban.com'+tag+'?type=S')

for tag_url in tag_urls:
	tag=tag_url[28:-7]
	i=0
	temp=5
	while(True):
		tag_url_2=tag_url+'&start='+str(i*20)
		print(tag_url_2)
		html = requests.get(tag_url_2,headers=headers)
		#print('请求:'+tag_url)
		selector = etree.HTML(html.text)
		infos = selector.xpath('//li[@class="subject-item"]/div[2]')
		print(len(infos))
		if len(infos)==0 :
			break
		for info in infos:
			try:
				name = info.xpath('h2/a/@title')[0]
				#print(name)
				url = info.xpath('h2/a/@href')[0]
				book_infos = info.xpath('div[1]/text()')[0]
				author = book_infos.split('/')[0]
				publisher = book_infos.split('/')[-3]
				date = book_infos.split('/')[-2]
				price = book_infos.split('/')[-1]
				rate = info.xpath('div[2]/span[2]/text()')[0]
				comments = info.xpath('p/text()')
				comment = comments[0] if len(comments)!=0 else "空"
				if float(rate) >= 9 or int(temp) > 0:
					print(temp)
					if float(rate) <9 :
						temp = temp-1
					writer.writerow((tag,name,url,author,publisher,date,price,rate,comment))
				else:
					break
			except :
				pass
		i=i+1
		if float(rate)<9:
			break
		
fp.close()