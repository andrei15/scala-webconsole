# Scala Web Console

This simple application delivers Scala REPL console over web.

![](http://cdn.savant.pro/home/communities/00/00/00/02/static/img/2f446010-1f02-42e9-a1f5-b794b591e6d2.png?1351944308000)

You can run it locally to perform all kinds of Scala tests and experiments.

Furthermore, you can easily embed it into your own web application to introduce
the "superadministrator" functionality.

## Building and running

1. Make sure you have official Oracle JDK 1.6 and Apache Maven 3.x installed on
   your computer.

2. Scala Web Console builds with latest snapshot of [Circumflex](http://github.com/inca/circumflex), so you need to clone and build it beforehand:

    ```
    git clone https://github.com/inca/circumflex
    cd circumflex
    mvn clean install
    ```

3. Now clone Scala Web Console to your computer and run it with Maven Jetty Plugin:

   ```
   git clone http://github.com/inca/scala-webconsole
   cd scala-webconsole
   mvn jetty:run
   ```

4. Visit [http://localhost:8180](http://localhost:8180) and enjoy your very own Scala Web Console!

## A word of warning

As you can see by browsing our sources, there are absolutely no security restrictions and limitations. If you embed this product into your production application, nothing will stop
users from executing `Runtime.getRuntime.exit(0)` or even worse. Be careful!

We encourage you to use this product strictly for educational or debugging purposes. Remember, we are not responsible for data loss, damage or any other destructive implications of using this product.

## License

Scala Web Console and all its parts are free to use in both commercial and non-commercial products.

Stylesheets, images and scripts used in this application belong to their respective owners
([SAVANT.PRO educational platform](http://savant.pro), [JQuery](http://jquery.com), [scala-lang.org](http://scala-lang.org)).

