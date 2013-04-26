Lindenmayer Systems
===================

An L-System or Lindenmayer system is a parallel rewriting system, namely a
variant of a formal grammar, most famously used to model the growth 
processes of plant development, but also able to model the morphology of
a variety of organisms.

This project is written using Clojure, demonstrating L-System 
re-writing, deployed to Heroku: 

* Use http://lindenmayer-systems.destructuring-bind.org/explorer/X - where x = 0..10. - 
  this will render the page server-side, and will deliver a PNG image; 

* Use http://lindenmayer-systems.destructuring-bind.org/random - 
  this will render the page server-side, and will deliver a PNG image; 
  refreshing will show a random L-system generation from a set of 11.

Roadmap
-------

###Implemented

* Prototyping with several different l-systems (see 
  http://lindenmayer-systems.destructuring-bind.org/ for an 
  on-going example) - see 'master' branch

* Generalized pattern compiler (see 'compiler' branch)

* Re-wrote turtle draw to allow HTML5 Canvas and PNG rendering plugins

* No longer uses Noir

* Implement flexible parallel (efficient) rewriting system and some example
  L-System curves. (see 'grammar' branch)

    - Heighways Dragon

    - Koch Curve

    - Sierpinski Curve / Triangle / Median Curve

    - Space-filling Curve

    - Penrose Tiling

### TODO

* UI improvements to allow editing and a last-created list


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

* http://teethgrinder.co.uk/canvas/l-system-1.html
