Lindenmayer Systems
===================

An L-System or Lindenmayer system is a parallel rewriting system, namely a
variant of a formal grammar, most famously used to model the growth 
processes of plant development, but also able to model the morphology of
a variety of organisms.

This project is a pure ClojureScript implementation, demonstrating L-System 
re-writing, deployed to Heroku: http://lindenmayer-systems.destructuring-bind.org/ - 
refreshing the page will show a random L-system generation from a set of 6.

Roadmap
-------

###Implemented

* Prototyping with several different l-systems (see 
  http://lindenmayer-systems.destructuring-bind.org/ for an 
  on-going example) - see 'master' branch

* Generalized pattern compiler (see 'compiler' branch)

* Implement flexible parallel (efficient) rewriting system and some example
  L-System curves. (see 'grammar' branch)

    - Heighways Dragon

    - Koch Curve

    - Sierpinski Curve / Triangle / Median Curve

    - Space-filling Curve

* Turtle implementation

    - Auto scaling / rotating for maximum use of available canvas area. 
    
    - Support rotation angles other than 90°

    - Colors

### TODO

* UI improvements to allow editing and a last-created list

* Command implementation
    
    - Push/pop context (position, angle and color)

* Proper support for initial conditions

* Re-write turtle draw to allow HTML5 Canvas and PNG rendering plugins

Implementation Notes
--------------------
TODO

References
----------
* [The Algorithmic Beauty of Plants](http://algorithmicbotany.org/papers/abop/abop.pdf) [PDF]

* [Project Euler #220: Heighway Dragon](http://projecteuler.net/problem=220)

* [Project Euler #226: A Scoop of Blancmange](http://projecteuler.net/problem=226)

* [Project Euler #230: Fibonacci Words](http://projecteuler.net/problem=230)

* https://en.wikipedia.org/wiki/Dragon_curve

* https://en.wikipedia.org/wiki/L-system

* [Sierpinski's Triangle](http://webrot.destructuring-bind.org/sierpinski?divisor=2) - observe the difference between prime and composite divisors

* http://www.kevs3d.co.uk/dev/lsystems/
