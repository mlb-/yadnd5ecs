# yadnd5ecs

Yet another Dugeons and Dragons 5th edition character sheet.

## Rationale

I'm a 3rd/3.5/Pathfinder kind of guy, but gave 5th edition a
chance. The jump to 5th edition was different enough that character
creation required assistance, and all character sheet tools I tried
were lackluster. I thought the state of the tools was unfortunate, so
I'm trying my hand.

### Implementation Roadmap

- Initially, I imagined Clojure's collections would make dice rolling
  simple to emulate. Then I started considering re-rolls, keeping the
  n highest or lowest, etc. It got complicated quickly.

- Then, in traditional LISP fashion, I considered data structures
  representing the formulas describing what to roll, as well as data
  structures representing the result of such rolls. A model,
  independent a UI designed around it, could be developed in
  depth. This could quickly become a rabbit hole and provide no
  progress made on the UI any time soon.

- And now, in traditional React fashion, I've considered simply
  providing a skeleton layout of all non-derived data. After which, I
  can proceed to create the logic to compute all derived values in the
  UI.

- Upon discovery of no available character sheet in the Player's
  Handbook I have available, I am now considering implementing this
  character sheet in the order presented for character creation.

## Development

Open a terminal and type `lein repl` to start a Clojure REPL
(interactive prompt).

In the REPL, type

```clojure
(run)
(browser-repl)
```

The call to `(run)` does two things, it starts the webserver at port
10555, and also the Figwheel server which takes care of live reloading
ClojureScript code and CSS. Give them some time to start.

Running `(browser-repl)` starts the Weasel REPL server, and drops you
into a ClojureScript REPL. Evaluating expressions here will only work
once you've loaded the page, so the browser can connect to Weasel.

When you see the line `Successfully compiled "resources/public/app.js"
in 21.36 seconds.`, you're ready to go. Browse to
`http://localhost:10555` and enjoy.

**Attention: It is not longer needed to run `lein figwheel`
  separately. This is now taken care of behind the scenes**

## Trying it out

If all is well you now have a browser window saying 'Hello Chestnut',
and a REPL prompt that looks like `cljs.user=>`.

Open `resources/public/css/style.css` and change some styling of the
H1 element. Notice how it's updated instantly in the browser.

Open `src/cljs/yadnd5ecs/core.cljs`, and change `dom/h1` to
`dom/h2`. As soon as you save the file, your browser is updated.

In the REPL, type

```
(ns yadnd5ecs.core)
(swap! app-state assoc :text "Interactivity FTW")
```

Notice again how the browser updates.

## Deploying to Heroku

This assumes you have a
[Heroku account](https://signup.heroku.com/dc), have installed the
[Heroku toolbelt](https://toolbelt.heroku.com/), and have done a
`heroku login` before.

``` sh
git init
git add -A
git commit
heroku create
git push heroku master:master
heroku open
```

## Running with Foreman

Heroku uses [Foreman](http://ddollar.github.io/foreman/) to run your
app, which uses the `Procfile` in your repository to figure out which
server command to run. Heroku also compiles and runs your code with a
Leiningen "production" profile, instead of "dev". To locally simulate
what Heroku does you can do:

``` sh
lein with-profile -dev,+production uberjar && foreman start
```

Now your app is running at
[http://localhost:5000](http://localhost:5000) in production mode.

## License

Copyright © 2014 Matthew Batema

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

## Chestnut

Created with [Chestnut](http://plexus.github.io/chestnut/) 0.8.1 (90e701e0).
