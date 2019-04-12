package me.albinus.akka.artery.example

import akka.actor.{ ActorSystem, Props }
import akka.cluster.Cluster
import me.albinus.commons.config.{ AlbinusConfig, AppType }

object Main extends App {

  private val config = AlbinusConfig.load(AppType.Server)

  if (config.getBoolean("many-cluster")) {

    val system1 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-1")
    )
    system1.actorOf(Props(new ClusterListener))

    val system2 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-2")
    )
    system2.actorOf(Props(new ClusterListener))

    val system3 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-3")
    )
    system3.actorOf(Props(new ClusterListener))

  } else {

    val system = ActorSystem("akka-artery-example", config)

    if (config.getList("akka.cluster.seed-nodes").isEmpty) {
      system.log.info("Going to a single-node cluster mode")
      Cluster(system).join(Cluster(system).selfAddress)
    }

    system.actorOf(Props(new ClusterListener))

  }

}
