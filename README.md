# Lindenmayer Systems
[![Build Status](https://secure.travis-ci.org/rm-hull/lindenmayer-systems.svg)](http://travis-ci.org/rm-hull/lindenmayer-systems)
[![Coverage Status](https://coveralls.io/repos/rm-hull/lindenmayer-systems/badge.svg?branch=master)](https://coveralls.io/r/rm-hull/lindenmayer-systems?branch=master)
[![Dependencies Status](https://versions.deps.co/rm-hull/lindenmayer-systems/status.svg)](https://versions.deps.co/rm-hull/lindenmayer-systems)
[![Docker Pulls](https://img.shields.io/docker/pulls/richardhull/lindenmayer-systems.svg?maxAge=2592000)](https://hub.docker.com/r/richardhull/lindenmayer-systems/)
[![Maintenance](https://img.shields.io/maintenance/yes/2018.svg?maxAge=2592000)]()

> **NOTE** This project is currently undergoing significant rework

An L-System or Lindenmayer system is a parallel rewriting system, namely a
variant of a formal grammar, most famously used to model the growth
processes of plant development, but also able to model the morphology of
a variety of organisms.

This project is written using Clojure, demonstrating L-System
re-writing, deployed to Heroku:

Pre-defined examples can be generated using these links:

#### [Sierpinski Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/sierpinski-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/sierpinski-curve.svg)

#### [Sierpinski Triangle](http://lindenmayer-systems.destructuring-bind.org/explorer/sierpinski-triangle)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/sierpinski-triangle.svg)

#### [Sierpinski Median Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/sierpinski-median-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/sierpinski-median-curve.svg)

#### [Koch Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-curve.svg)

#### [Koch Snowflake](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-snowflake)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-snowflake.svg)

#### [Koch Ring](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-ring)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-ring.svg)

#### [Koch Blocks](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-blocks)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-blocks.svg)

#### [Koch Islands](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-islands)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-islands.svg)

#### [Koch Crystal](http://lindenmayer-systems.destructuring-bind.org/explorer/koch-crystal)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/koch-crystal.svg)

#### [Quadratic Koch Island](http://lindenmayer-systems.destructuring-bind.org/explorer/quadratic-koch-island)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/quadratic-koch-island.svg)

#### [Cesaro-Koch Fractal](http://lindenmayer-systems.destructuring-bind.org/explorer/cesaro-koch-fractal)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/cesaro-koch-fractal.svg)

#### [Joined-Cross Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/joined-cross-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/joined-cross-curve.svg)

#### [Penrose Tiling](http://lindenmayer-systems.destructuring-bind.org/explorer/penrose-tiling)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/penrose-tiling.svg)

#### [Peano-Gosper Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/peano-gosper-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/peano-gosper-curve.svg)

#### [Hilbert's Space-filling Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/hilberts-space-filling-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/hilberts-space-filling-curve.svg)

#### [FASS Curve](http://lindenmayer-systems.destructuring-bind.org/explorer/fass-curve)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/fass-curve.svg)

#### [Heighway's Dragon](http://lindenmayer-systems.destructuring-bind.org/explorer/heighways-dragon)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/heighways-dragon.svg)

#### [Shrub](http://lindenmayer-systems.destructuring-bind.org/explorer/shrub)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/shrub.svg)

#### [Fractal Plant](http://lindenmayer-systems.destructuring-bind.org/explorer/fractal-plant)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/fractal-plant.svg)

#### [Tree](http://lindenmayer-systems.destructuring-bind.org/explorer/tree)
![SVG](https://rawgithub.com/rm-hull/lindenmayer-systems/master/doc/examples/tree.svg)

## Pre-requisites

You will need [Leiningen](https://github.com/technomancy/leiningen) 2.6.1 or above installed.

## Building

To build and start the service locally, run:

    $ cd lindenmayer-systems
    $ lein deps
    $ lein ring server-headless

To build and run a standalone jar:

    $ lein ring uberjar
    $ java -jar target/lindenmayer-systems-0.2.0-SNAPSHOT-standalone.jar

In both instances, the webapp starts on http://localhost:3000.

### Docker image

A docker image is available as [richardhull/lindenmayer-systems](https://hub.docker.com/r/richardhull/lindenmayer-systems),
and can be downloaded and started with:

    $ docker pull richardhull/lindenmayer-systems
    $ docker run --name lindenmayer-systems -d -p 3000:3000 richardhull/lindenmayer-systems

## TODO

* UI improvements to allow editing and a last-created list

## Implementation Notes

TODO

## References

* [The Algorithmic Beauty of Plants](http://algorithmicbotany.org/papers/abop/abop.pdf) [PDF]
* [Project Euler #220: Heighway Dragon](http://projecteuler.net/problem=220)
* [Project Euler #226: A Scoop of Blancmange](http://projecteuler.net/problem=226)
* [Project Euler #230: Fibonacci Words](http://projecteuler.net/problem=230)
* https://en.wikipedia.org/wiki/Dragon_curve
* https://en.wikipedia.org/wiki/L-system
* http://www.kevs3d.co.uk/dev/lsystems/
* http://teethgrinder.co.uk/canvas/l-system-1.html
* http://paulbourke.net/fractals/lsys/
* http://canonical.org/~kragen/named-msgs/reptile-lsystems


## License

### The MIT License (MIT)

Copyright (c) 2016 Richard Hull

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

