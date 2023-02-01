#!/usr/bin/env zsh
rm -rf checker/resources
mkdir checker/resources

if [ "$1" = "new" ]
then
  cp -r checker/res2/* checker/resources
else
  cp -r checker/res1/* checker/resources
fi

