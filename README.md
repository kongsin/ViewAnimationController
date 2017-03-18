# ViewAnimatorController

![Sample Image](https://github.com/kongsin/SlideTab/blob/master/videotogif_2017.03.18_18.00.04.gif)
![Sample Image](https://github.com/kongsin/ViewAnimatorController/blob/master/videotogif_2017.03.19_04.04.53.gif)

- example code
_https://github.com/kongsin/SlideTab_

------

* BaseAnimationControl is an animation object
    ```JAVA
     BaseAnimationControl imageAnimObj = new BaseAnimationControl(img);
    ```
* AnimationQueue for manage queue of the animation object
- AnimationQueue.nextQueue for add animation queue
- AnimationQueue.startByQueue for start as a queue
- AnimationQueue.startTogether for play all animation together

```JAVA
                AnimationQueue animationQueue = new AnimationQueue();
                animationQueue.nextQueue(0, b1);                
                animationQueue.nextQueue(0, b2);
                animationQueue.nextQueue(0, b3);
                animationQueue.startByQueue();
                //or
                //animationQueue.startTogether();
```

### Example
```JAVA
                BaseAnimationControl b1 = new BaseAnimationControl(img).moveToCenterVertical(main).moveToCenterHorizontal(main);
                
                BaseAnimationControl b2 = new BaseAnimationControl(img2);
                b2.stackToLeftOf(b1).marginRight(50);
                
                BaseAnimationControl b3 = new BaseAnimationControl(img3);
                b3.stackToRightOf(b1).marginLeft(50);
                
                AnimationQueue animationQueue = new AnimationQueue();
                animationQueue.nextQueue(0, b1);                
                animationQueue.nextQueue(0, b2);
                animationQueue.nextQueue(0, b3);
                animationQueue.setCallback(new AnimationQueue.AnimatedCallback() {
                    @Override
                    public void finished() {

                    }

                    @Override
                    public void eachQueueFinished(BaseAnimationControl control) {
                        Log.i(TAG, "eachQueueFinished: " + control.getView().getId());
                    }
                });
                
                animationQueue.startByQueue();
```
