class Settings():
	def __init__(self):
		self.screen_width = 800
		self.screen_height =600
		self.bg_color = (230,230,230)
		self.ship_speed_factor = 0.5
		
		self.bullet_speed_factor = 1
		self.bullet_width = 3
		self.bullet_height = 15
		self.bullet_color = 60,60,60
		self.bullet_allowed = 3
		
		self.alien_apeed_factor = 1
		self.fleet_drop_speed = 10
		self.fleet_direction = 1