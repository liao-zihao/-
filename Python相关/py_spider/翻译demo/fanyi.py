#实测GG
import requests
import json
headers={
	'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0'
}
word=input("请输入中文:")
url='http://howtospeak.org:443/api/e2c?user_key=dfcacb6404295f9ed9e430f67b641a8e&notrans=0&text={}'.format(word)
res=requests.get(url,headers=headers)
json_data=json.loads(res.text)
english_word=json_data['english']
print(english_word)