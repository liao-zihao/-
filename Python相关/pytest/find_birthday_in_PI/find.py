filename = 'pi_million_digits.txt'

with open(filename) as file_object:
	lines=file_object.readlines();
	
pi_string = ' '
for line in lines:
	pi_string+=line.rstrip()

birthday=str(317)
if birthday in pi_string:
	print("Your birthday in first million of PI")
else:
	print("Your birthday not in first million of PI")