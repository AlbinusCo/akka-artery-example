package click.rashad.akka.artery.example.sharding

import akka.actor._
import akka.cluster.sharding._

object ShardingExtension extends ExtensionId[ShardingExtensionImpl] {
  override def createExtension(system: ExtendedActorSystem): ShardingExtensionImpl =
    new ShardingExtensionImpl(system)
}

final class ShardingExtensionImpl(system: ActorSystem) extends Extension {

  private val shardNumbers = 30

  private val extractShardId: ShardRegion.ExtractShardId = {
    case ShardModel(id) ⇒ (id % shardNumbers).toString
  }

  private val extractEntityId: ShardRegion.ExtractEntityId = {
    case data @ ShardModel(id) ⇒ (id.toString, data)
  }

  val shardRegion: ActorRef = ClusterSharding(system).start(
    typeName = "ShardModel",
    entityProps = Props(new ShardActor),
    settings = ClusterShardingSettings(system),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId
  )

}
