
Some stuff on pub

http://pub.dartlang.org/doc/dependencies.html#sources

Pull from SCM:

This DOES work - see pubspec.yaml.  however it is still a bit of
a suckfest because we have to find the bug, code a potential fix, push
to git, pub update the library locally, test the fix, find it isn't fixed,
repeat the cycle.  Better than pushing anything to public but still 
blechy IMO.

dependencies:
  kittens:
    git:
      url: git://github.com/munificent/kittens.git
      ref: some-branch
      
Pull from URL:

dependencies:
  transmogrify:
    hosted:
      name: transmogrify
      url: http://your-package-server.com
    version: '>=0.4.0 <1.0.0'
    
    
Pull from Local (good for dev mode)

THIS ONE DOES NOT APPEAR TO WORK!

dependencies:
  transmogrify:
    path: /Users/me/transmogrify