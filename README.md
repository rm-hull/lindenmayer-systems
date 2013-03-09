Lindenmayer Systems
===================

An L-System or Lindenmayer system is a parallel rewriting system, namely a
variant of a formal grammar, most famously used to model the growth 
processes of plant development, but also able to model the morphology of
a variety of organisms.

This project is a pure ClojureScript implementation, demonstrating L-System 
re-writing, deployed to Heroku: http://lindenmayer-systems.destructuring-bind.org/

Roadmap
-------

###Implemented

* Prototyping with simple lazy Heighway's Dragon (see 
  http://lindenmayer-systems.destructuring-bind.org/ for an 
  on-going example) - master branch

* Implement flexible parallel (efficient) rewriting system and some example
  L-System curves. - grammar branch

    * Heighways Dragon

    * Koch Curve

    * 'Square' Sierpinski

### TODO

* Auto scaling / rotating for maximum use of available canvas area. 

* Push/pop context (position and angle)

* Support rotation angles other than 90°

* Generalized compiler

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
