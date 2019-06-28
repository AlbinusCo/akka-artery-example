package click.rashad.akka.artery.example.sharding

import akka.actor.Actor
import akka.event.Logging

final class ShardActor extends Actor {

  import context.system

  private val log = Logging(system, getClass)

  override def receive: Receive = {
    case ShardModel(id) ⇒
      log.info("Shard actor receive shard model with id {}", id)
  }

}
