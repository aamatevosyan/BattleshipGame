All source files are located in src.
Jar file is located in mods/artifacts/BattleshipGame_jar.
To run Jar file, please edit JavaFX path in startGame.bat and launch startGame.bat from Command Prompt.

All the test cases were associated with only hse.edu.battleship.core package, which presents all the classes that were
used in HW-1. UI tests were not created as their result can be seen only while all the system is working. So you can
see it by just lunching and playing this game.

As for the framework for GUI the JavaFX was used with FXML files created by SceneBuilder. All windows have their
Controller classes.

After the launch there is a splash window which will be open until closing by button. To start a game please choose
"Start new battle, arr!", and "Pathetic run, loser!" for exit. Then you will need to choose to play solo or to play by
network. For network play only ships selection is realised, but as for this HW its not required, so please be gentle
with grading it. Also you can choose "Random" if you want to get some random board with ships already allocated.

The GameWindow allow user to play using only keyboard by moving from control to control by tab and by clicking it by
enter. You can type coordinates separated by space in textfield and click to button "Shoot". If the coordinates were not
correct, message will be shown. Also you can get to the ocean cells by tab and move from it using "moving keys" and
enter to shoot at the chosen cell. Also you can click to the cell using mouse, if cell is accessed by keyboard or is
hovered by mouse, the cell will get focused and you will see dark borders around that cell. If the game is over, the
message will be shown with the score and there you can choose between exiting game and starting new game.

As for ship selection window, you need to place all ships and click to Ok button, if something will be wrong or if you
click cancel, random ocean field will be used further. You can choose orientation "Vertical" or "Horizontal" and you can
revert your last ship selection by "Revert" and you can clear field by "Clear". You can navigate in ocean using moving
pointer keys. For more comfortable access there were some shortcuts to this buttons: "Vertical" - V, "Horizontal" - H,
"Revert" - R, "Clear" - C.

As for getting all messages to "log field", custom class was used as some bridge between field and System.out stream.