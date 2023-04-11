# PPM Parser

`Author : Rotari Corneliu` `Group : 323 CD`

___

## Table of Contents

- [Prerequisites](#prerequisites)
- [Concatenations and Rotation](#concatenations-and-rotation)
- [Edge Detection](#edge-detection)
- [Pascal Triangle](#pascal-triangle)

___

## Prerequisites

PPM to Matrix converts PPM image format to pixel matrix, while Matrix to PPM does the inverse by converting pixel matrix to PPM format.

___

## Concatenations and Rotation

Concat two Images of the same size vertically or horizontally.
Rotates the matrix in the multiples of 90. It can be optimized to only 4 cases in every type of rotations.

___

## Edge Detection

By mapping the image to it's Grayscale proportions.
Applied convolutions , by blur , on Ox and Oy axis, we combine the results to create a double matrix.

![](https://ocw.cs.pub.ro/ppcarte/lib/exe/fetch.php?cache=&media=pp:2023:convolution.gif)

Applied a threshold to map the white and black pixels to see the underling lines.

___

## Pascal Triangle

Dynamic programmed to utilize the previous lines calculate the next Combination Module.
The matrix are added in reverse into the matrix, so we reverse every line's columns and every line.


<img style="background-color: white" src="https://ocw.cs.pub.ro/ppcarte/lib/exe/fetch.php?cache=&media=pp:2023:hw1_pascal_visualization.png" alt="PT">

___
