package me.albinus.akka.artery.example

import akka.actor._
import akka.cluster.Cluster
import akka.management.scaladsl.AkkaManagement
import me.albinus.akka.artery.example.cluster.ClusterListener
import me.albinus.akka.artery.example.sharding.ShardingExtension
import me.albinus.commons.config._

object Main extends App {

  private val config = AlbinusConfig.load(AppType.Server)

  if (config.getBoolean("many-cluster")) {

    val system1 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-1")
    )
    AkkaManagement(system1).start()
    ShardingExtension(system1)
    system1.actorOf(Props(new ClusterListener))

    val system2 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-2")
    )
    AkkaManagement(system2).start()
    ShardingExtension(system2)
    system2.actorOf(Props(new ClusterListener))

    val system3 = ActorSystem(
      "akka-artery-example",
      AlbinusConfig.load(AppType.Server).getConfig("akka-system-3")
    )
    AkkaManagement(system3).start()
    ShardingExtension(system3)
    system3.actorOf(Props(new ClusterListener))

  } else {

    val system = ActorSystem("akka-artery-example", config)

    if (config.getList("akka.cluster.seed-nodes").isEmpty) {
      system.log.info("Going to a single-node cluster mode")
      Cluster(system).join(Cluster(system).selfAddress)
    }

    AkkaManagement(system).start()
    ShardingExtension(system)
    system.actorOf(Props(new ClusterListener))

  }

}
