# PaykeyAssignment

## Goal
Create a Keyboard Calculator. The requirements can be found here: https://docs.google.com/document/d/1G8t9BPI_RfUaQ1sgs7doe4CkziJPDQzAyOs9zuCe7gA/edit?usp=sharing

## Implementation comments

* I have used MVP paradigm and Dagger library for dependency Injection.
* Simple line-by line file storage is used to store input history.
* I haven't created unit tests except for ArithmeticEvaluator that I used for debugging of the evaluator.

## Possible improvements (what would I do if I have more time)

* When history list is shown, text input is disabled, but this is not reflected by the UI. I couldn't find a way to disable keys in KeyboardView. Probably a custom view should be used for keyboard instead.
* When the list appears/disapears, Android redraws all the keyboard view and replays keyboard show animation. This doesn't look good, so the way to control this animation should be found.
* I don't use ListView.onItemClickListener, as this seem to be not working in old Android versions (it works on Android 22 and higher, but doesn't on 14 and 16. I guess that is why it is mentioned as a bonus). So I use onClickListener for list item views instead.
* Currently history lict can contain any number of equal items. This can be changed to store only unique items.
* Integer overflow check should be added.
