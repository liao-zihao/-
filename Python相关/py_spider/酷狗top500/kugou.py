import requests
from bs4 import BeautifulSoup//比较好用
import time

headers={
	'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0'
}

def get_info(url):
	wb_data = requests.get(url,headers=headers)//请求
	soup=BeautifulSoup(wb_data.text,'lxml')//转换
	ranks = soup.select('span.pc_temp_num')//选择器
	titles = soup.select('div.pc_temp_songlist>ul>li>a')
	times = soup.select('span.pc_temp_tips_r>span')
	
	for rank,title,time in zip(ranks,titles,times):
		data = {
			'rank':rank.get_text().strip(),
			'singer':title.get_text().split('-')[0],
			'song':title.get_text().split('-')[1],
			'time':time.get_text().strip()
		}
		print(data)
	
if __name__=='__main__':
	urls = ['https://www.kugou.com/yy/rank/home/{}-8888.html'.format(str(i)) for i in range(1,24)]
	for url in urls:
		get_info(url)
	time.sleep(1)