to recreate:

set 
`sbt.version=1.1.0` in `project/build.properties`

and run 
```
sbt myKey
```

will produce an exception:
> com.typesafe.config.ConfigException$UnresolvedSubstitution: module.conf: 8: Could not resolve substitution to a value: ${module1.App.Args.param1}

----

setting `sbt.version=0.13.16` in `project/build.properties`  
wil work properly
