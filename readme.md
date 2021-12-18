
# Pixel Art(illery)

---

## Overview

This is a small framework for creating '2D' games with OpenGL.
The whole framework based on [LWJGL][lwjgl] and [JOML][joml].
The repository includes several Maven modules:

| Module    | Description                                                                      |
| --------- | -------------------------------------------------------------------------------- |
| examples  | a short introduction on, how to use the framework                                |
| framework | the framework itself aka 'The Engine' aka 'The Heart' aka 'The Root of all Evil' |
| tools     | some tools for making the creation of games easier                               |

### Please read
When you clone the repository and want to build it please check the `lwjgl.natives` property inside the [parent pom][parent-pom].
The following options are available, depending on your system:
* `natives-macos`
* `natives-linux`
* `natives-windows`

If you are running on Mac OS you need to start the application with `-XstartOnFirstThread` as a VM option.

---

## Documentation

* Framework
* [Tools][tools]
* Examples

[joml]: https://github.com/JOML-CI/JOML
[lwjgl]: https://www.lwjgl.org/
[parent-pom]: pom.xml
[tools]: tools/documentation/readme.md
