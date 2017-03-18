# ViewAnimatorController

![Sample Image](https://github.com/kongsin/SlideTab/blob/master/videotogif_2017.03.18_18.00.04.gif)
![Sample Image](https://github.com/kongsin/ViewAnimatorController/blob/master/videotogif_2017.03.19_04.04.53.gif)

------

### Example
```JAVA
                BaseAnimationControl b1 = new ImageAnimationControl(img).moveToCenterVertical(main).moveToCenterHorizontal(main);
                
                BaseAnimationControl b2 = new ImageAnimationControl(img2);
                b2.stackToLeftOf(b1).marginRight(50);
                
                BaseAnimationControl b3 = new ImageAnimationControl(img3);
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
