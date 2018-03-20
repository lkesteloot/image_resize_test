
.PHONY: all

all: small.png python.png magick.png magick-gamma.png java.png

# Checkerboard input.
large.png: make_checkerboard Makefile
	./make_checkerboard large.png 512

# Checkerboard small, for visual comparison.
small.png: make_checkerboard Makefile
	./make_checkerboard small.png 256

# Resize using Python.
python.png: python_resize large.png
	./python_resize large.png python.png

# Resize using ImageMagick
magick.png: large.png
	convert large.png -resize 256 -seed 0 -strip magick.png

# Resize using ImageMagick and gamma flag.
# https://www.imagemagick.org/script/command-line-options.php#gamma
magick-gamma.png: large.png Makefile
	convert large.png -resize 256 -seed 0 -strip -gamma 2.2 magick-gamma.png

JavaResize.class: JavaResize.java
	javac JavaResize.java

java.png: JavaResize.class large.png
	java JavaResize large.png java.png
