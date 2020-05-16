## Sequoia, a simple static-site generator

Created for Professor Hamid's CSC-207 class.

In order to use, download the jar from the jar folder on the repository,
and execute the jar inside the folder you want to create, or execute
the project `sequoia new <DIRNAME>` directory, e.g. `java -jar sequoia new`,
then use `java -jar sequoia build <PUBLIC>` in the directory to
build. Then switch to public to see the generated HTML
templates! You can also modify the templates around the Mustache.js
tags to add in Bootstrap, Bulma, etc.

Alternatively, create a symlink to run the jar as an installed
application. You can also build from scratch with Maven.

Use the starter YAML to construct your own pages!
Each base layer uses the base template, and subtemplates
can be introduced by creating a subtree in YAML with a type: <TYPE>
property. Besides `name`, `key`, and `url`, other key names
are vestigial, for now.

Make sure to put quotes around numbers and booleans (including `yes` and `no`),
since Sequoia only supports Strings for now.