// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/frudisch/Projekt/Private/Manager/team-service/conf/routes
// @DATE:Sun Sep 02 14:36:10 CEST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
