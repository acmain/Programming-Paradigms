Planning:
	Requirements:
		displayable instructions while running the game
		player controlled character
		AIs
		threads to control updates
		pauseable (display instructions at same time?)

	Premise:
		Cat explores city looking for fish
		Must avoid dogs
		top down 2-D
		
	Class Hierarchy:
		Graphic //similar to sprite
			GUI //primary city overlay
			Menu //starting and pause menu
			Animal //primary class for moving images
				Cat
				Dog
				Fish
		Window //similar to view
		Graphics //holds arraylist of images (model)
		Play //similar to controller