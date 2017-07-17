$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("loadHitrate.feature");
formatter.feature({
  "line": 1,
  "name": "Load hit rate data",
  "description": "",
  "id": "load-hit-rate-data",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Search top 5 hit rate websites",
  "description": "",
  "id": "load-hit-rate-data;search-top-5-hit-rate-websites",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I have the following hit rate data",
  "rows": [
    {
      "cells": [
        "visit_date",
        "website",
        "visits"
      ],
      "line": 6
    },
    {
      "cells": [
        "2016-01-06",
        "www.bing.com",
        "14065457"
      ],
      "line": 7
    },
    {
      "cells": [
        "2016-01-06",
        "www.ebay.com.au",
        "19831166"
      ],
      "line": 8
    },
    {
      "cells": [
        "2016-01-06",
        "www.facebook.com",
        "104346720"
      ],
      "line": 9
    },
    {
      "cells": [
        "2016-01-06",
        "mail.live.com",
        "21536612"
      ],
      "line": 10
    },
    {
      "cells": [
        "2016-01-06",
        "www.wikipedia.org",
        "13246531"
      ],
      "line": 11
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "hit rate loader is run",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "the following hit rate data should be loaded",
  "rows": [
    {
      "cells": [
        "visit_date",
        "website",
        "visits"
      ],
      "line": 14
    },
    {
      "cells": [
        "2016-01-06",
        "www.bing.com",
        "14065457"
      ],
      "line": 15
    },
    {
      "cells": [
        "2016-01-06",
        "www.ebay.com.au",
        "19831166"
      ],
      "line": 16
    },
    {
      "cells": [
        "2016-01-06",
        "www.facebook.com",
        "104346720"
      ],
      "line": 17
    },
    {
      "cells": [
        "2016-01-06",
        "mail.live.com",
        "21536612"
      ],
      "line": 18
    },
    {
      "cells": [
        "2016-01-06",
        "www.wikipedia.org",
        "13246531"
      ],
      "line": 19
    }
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});