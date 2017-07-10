# ps45 - Lorenzo Il Magnifico

### Developed requirements
- Simplified rules
- Complete rules (with the exception that Leader Cards are randomly chosen for each player at the start of the game instead of them choosing them one by one)
- CLI
- GUI
- Socket

### UML
The UML is located at /doc/UML.png in the project folder.
(Try to open it using an image viewer because the image is quite big and your browser might crash.)

### How to run
1. Open a new terminal in the project folder then run the server using
    `java -jar s.jar`
2. To open the client open another terminal in the same folder then type
    `java -jar c.jar gui` //to use GUI    or
    `java -jar c.jar cli` // to use CLI
3. The game will start after four players joined the game or the game start timer ran out of time(currently set to 180 seconds - you can modify it by modifying the json files in serialized/time/).


### GUI Instructions
After the welcome windows opens, choose your name, the IP address of the server and the bonus tile you want to use.
After the game starts, you can send commands to the server using the "Control Widow". 
The GUI automatically checks then lists all available commands/areas/pawns that you can send at that time.

Some advanced commands require additional user input which you can enter in the Control Window's text field.
To do a production you have to also add the production choice in the Control Window's text field(because some buildings have two options for productions) like this: `110022`
	(110022 means that the first(1) option will be used for your first 2 buildings, (0) the production will be skipped for the next two buildings and (2) the second option will be used for the last two buildings).
To acquire a venture you also have to write which price will you use(some ventures have 2 prices): The two options are `first` or `second`.
While exchanging a council privilege or discarding a leader card you also have to specify which type of council prigilege you want.
Available types are:

	"coins", "faith", "military", "servants", "woodandstone".


After you've chosen the command you want to send, just press the send command button and the command should be sent to the server. 

Don't forget to end your turn by sending and `endturn` command.


### CLI Instructions
--- Commands ---

The general syntax is:
	{action}-{area}-{pawn}-{servants added}-({mode})

So for example if you want to place the white pawn on the ground floor of the territory tower and add 1 servant:

	placepawnterritory-t0-white-1
	
If you want to take a territory without placing a pawn:

	nopawnterritory-t1-1` (skipping the pawn selection).
	
To acquire a venture you also have to write which price will you use(some ventures have 2 prices):

	placepawnventure-v2-orange-0-first
	(available options are "first" and "second").

To end your turn you just write:
	`endturn`

To do a harvest/production you first have to place a pawn on the area:

	placepawnharvest-small-orange-2
	
Then actually execute the harvest writing:

	harvest
	
To do a production you have to also add the production choice(because some buildings have two options for productions) like this:

	placepawnproduction-big-black-3
	
	production-110022
	
	(110022 means that the first(1) option will be used for your first 2 buildings, the production will be skipped(0) for the next two and the second(2) option will be used for the last two buildings).

To accept vatican/refuse vatican:
	`acceptvatican`
	    (or)
	`refusevatican`

To exchange council privileges:

	cp1-{type}(exchange one council privilege)
	
	cp2-{type1}-{type2} (exchange two different council privileges)
	
	cpe-{type1}-{type2}-{type3} (exchange three different council privileges)
	
Available types are:
	"coins", "faith", "military", "servants", "woodandstone".

To activate/use a Leader Card:

	activateleader-{index}
	useleader-{index}
	useleader-{index}-{council privilege type}
	
	(index being the index of the card you want to activate in your Leader Card list.)
	
	activateleader-2 (activated the third card in your leader card list).
	useleader-0 (use the first card in your activated leader card list).

--- Areas ---

`coinsmarketarea` (Market area that gives you coins)

`servantsmarketarea` (Market area that gives you servants)

`militarymndcoinarea` (Market area that gives you coins and military points)

`councilprivilegemarketarea` (Market area that lets you exchange a council privilege)

`councilpalacearea` (Council palace area)

`small` (The small harvest/production areas)

`big` (The big harvest/production areas)

`t0` (Territory ground floor)

`t1` (Territory first floor)

`t2` (Territory second floor)

`t3` (Territory third floor)

`c0` (Character ground floor)

`c1` (Character first floor)

`c2` (Character second floor)

`c3` (Character third floor)

`b0` (Building ground floor)

`b1` (Building first floor)

`b2` (Building second floor)

`b3` (Building third floor)

`v0` (Venture ground floor)

`v1` (venture first floor)

`v2` (venture second floor)

`v3` (venture third floor)


--- Pawns ---
`white`

`black`

`orange`

`neutral`
	
	

	
