<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
  <title></title>
  <link rel="stylesheet" type="text/css" href="../asset/index/css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="../asset/index/css/demo.css" />
  <link rel="stylesheet" type="text/css" href="../asset/index/css/font-awesome.css" />
  <!--必要样式-->
  <link rel="stylesheet" type="text/css" href="../asset/index/css/component.css" />

  <!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>
<body>
<div class="container">
  <div id="morphsearch" class="morphsearch">
    <form class="morphsearch-form" action="search" method="post">
      <input class="morphsearch-input" type="search" name="keyword" placeholder="Search..."/>
      <input class="morphsearch-submit" type="submit" value="提交"/>
    </form>
    <div class="morphsearch-content">
      <div class="dummy-column">
        <h2>People</h2>
        <a class="dummy-media-object" href="http://twitter.com/SaraSoueidan">
          <img src="../asset/index/img/thumbs/Whale3.png" alt="ResponsiveSVGs"/>
          <h3>Sara Soueidan</h3>
        </a>
        <a class="dummy-media-object" href="http://twitter.com/rachsmithtweets">
          <img src="img/thumbs/FreebieHalcyonDays.png" alt="FreebieHalcyonDays"/>
          <h3>Rachel Smith</h3>
        </a>
        <a class="dummy-media-object" href="http://www.twitter.com/peterfinlan">
          <img src="img/thumbs/OffCanvas.png" alt="OffCanvas"/>
          <h3>Peter Finlan</h3>
        </a>
        <a class="dummy-media-object" href="http://www.twitter.com/pcridesagain">
          <img src="img/thumbs/HoverEffectsIdeasNew.png" alt="HoverEffectsIdeasNew"/>
          <h3>Patrick Cox</h3>
        </a>
        <a class="dummy-media-object" href="https://twitter.com/twholman">
          <img src="img/thumbs/DraggableDualViewSlideshow.png" alt="DraggableDualViewSlideshow"/>
          <h3>Tim Holman</h3>
        </a>
        <a class="dummy-media-object" href="https://twitter.com/shaund0na">
          <img src="img/thumbs/PagePreloadingEffect.png" alt="PagePreloadingEffect"/>
          <h3>Shaun Dona</h3>
        </a>
      </div>
      <div class="dummy-column">
        <h2>Popular</h2>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/08/05/page-preloading-effect/">
          <img src="img/thumbs/PagePreloadingEffect.png" alt="PagePreloadingEffect"/>
          <h3>Page Preloading Effect</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/05/28/arrow-navigation-styles/">
          <img src="img/thumbs/ArrowNavigationStyles.png" alt="ArrowNavigationStyles"/>
          <h3>Arrow Navigation Styles</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/06/19/ideas-for-subtle-hover-effects/">
          <img src="img/thumbs/HoverEffectsIdeasNew.png" alt="HoverEffectsIdeasNew"/>
          <h3>Ideas for Subtle Hover Effects</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/07/14/freebie-halcyon-days-one-page-website-template/">
          <img src="img/thumbs/FreebieHalcyonDays.png" alt="FreebieHalcyonDays"/>
          <h3>Halcyon Days Template</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/05/22/inspiration-for-article-intro-effects/">
          <img src="img/thumbs/ArticleIntroEffects.png" alt="ArticleIntroEffects"/>
          <h3>Inspiration for Article Intro Effects</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/06/26/draggable-dual-view-slideshow/">
          <img src="img/thumbs/DraggableDualViewSlideshow.png" alt="DraggableDualViewSlideshow"/>
          <h3>Draggable Dual-View Slideshow</h3>
        </a>
      </div>
      <div class="dummy-column">
        <h2>Recent</h2>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/10/07/tooltip-styles-inspiration/">
          <img src="img/thumbs/TooltipStylesInspiration.png" alt="TooltipStylesInspiration"/>
          <h3>Tooltip Styles Inspiration</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/09/23/animated-background-headers/">
          <img src="img/thumbs/AnimatedHeaderBackgrounds.png" alt="AnimatedHeaderBackgrounds"/>
          <h3>Animated Background Headers</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/09/16/off-canvas-menu-effects/">
          <img src="img/thumbs/OffCanvas.png" alt="OffCanvas"/>
          <h3>Off-Canvas Menu Effects</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/09/02/tab-styles-inspiration/">
          <img src="img/thumbs/TabStyles.png" alt="TabStyles"/>
          <h3>Tab Styles Inspiration</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/08/19/making-svgs-responsive-with-css/">
          <img src="img/thumbs/ResponsiveSVGs.png" alt="ResponsiveSVGs"/>
          <h3>Make SVGs Responsive with CSS</h3>
        </a>
        <a class="dummy-media-object" href="http://tympanus.net/codrops/2014/07/23/notification-styles-inspiration/">
          <img src="img/thumbs/NotificationStyles.png" alt="NotificationStyles"/>
          <h3>Notification Styles Inspiration</h3>
        </a>
      </div>
    </div><!-- /morphsearch-content -->
    <span class="morphsearch-close"></span>
  </div><!-- /morphsearch -->

  <div class="overlay"></div>

</div><!-- /container -->

<script type="text/javascript" src="index/js/classie.js"></script>
<script type="text/javascript">
    (function() {
        var morphSearch = document.getElementById( 'morphsearch' ),
            input = morphSearch.querySelector( 'input.morphsearch-input' ),
            ctrlClose = morphSearch.querySelector( 'span.morphsearch-close' ),
            isOpen = isAnimating = false,
            // show/hide search area
            toggleSearch = function(evt) {
                // return if open and the input gets focused
                if( evt.type.toLowerCase() === 'focus' && isOpen ) return false;

                var offsets = morphsearch.getBoundingClientRect();
                if( isOpen ) {
                    classie.remove( morphSearch, 'open' );

                    // trick to hide input text once the search overlay closes
                    // todo: hardcoded times, should be done after transition ends
                    if( input.value !== '' ) {
                        setTimeout(function() {
                            classie.add( morphSearch, 'hideInput' );
                            setTimeout(function() {
                                classie.remove( morphSearch, 'hideInput' );
                                input.value = '';
                            }, 300 );
                        }, 500);
                    }

                    input.blur();
                }
                else {
                    classie.add( morphSearch, 'open' );
                }
                isOpen = !isOpen;
            };

        // events
        input.addEventListener( 'focus', toggleSearch );
        ctrlClose.addEventListener( 'click', toggleSearch );
        // esc key closes search overlay
        // keyboard navigation events
        document.addEventListener( 'keydown', function( ev ) {
            var keyCode = ev.keyCode || ev.which;
            if( keyCode === 27 && isOpen ) {
                toggleSearch(ev);
            }
        } );


        /***** for demo purposes only: don't allow to submit the form *****/
        morphSearch.querySelector( 'button[type="submit"]' ).addEventListener( 'click', function(ev) { ev.preventDefault(); } );
    })();
</script>

</body>
</html>
