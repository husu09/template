package com.su.akka.iot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.AbstractActor.Receive;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.FiniteDuration;

public class DeviceGroup extends AbstractActor {
	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	final String groupId;

	public DeviceGroup(String groupId) {
		this.groupId = groupId;
	}

	public static Props props(String groupId) {
		return Props.create(DeviceGroup.class, groupId);
	}

	public static final class RequestDeviceList {
		final long requestId;

		public RequestDeviceList(long requestId) {
			this.requestId = requestId;
		}
	}

	public static final class ReplyDeviceList {
		final long requestId;
		final Set<String> ids;

		public ReplyDeviceList(long requestId, Set<String> ids) {
			this.requestId = requestId;
			this.ids = ids;
		}
	}

	public static final class RequestAllTemperatures {
		final long requestId;

		public RequestAllTemperatures(long requestId) {
			this.requestId = requestId;
		}
	}

	public static final class RespondAllTemperatures {
		final long requestId;
		final Map<String, TemperatureReading> temperatures;

		public RespondAllTemperatures(long requestId, Map<String, TemperatureReading> temperatures) {
			this.requestId = requestId;
			this.temperatures = temperatures;
		}
	}

	public static interface TemperatureReading {
	}

	public static final class Temperature implements TemperatureReading {
		public final double value;

		public Temperature(double value) {
			this.value = value;
		}
	}

	public static final class TemperatureNotAvailable implements TemperatureReading {
	}

	public static final class DeviceNotAvailable implements TemperatureReading {
	}

	public static final class DeviceTimedOut implements TemperatureReading {
	}

	final Map<String, ActorRef> deviceIdToActor = new HashMap<>();
	final Map<ActorRef, String> actorToDeviceId = new HashMap<>();
	final long nextCollectionId = 0L;

	@Override
	public void preStart() {
		log.info("DeviceGroup {} started", groupId);
	}

	@Override
	public void postStop() {
		log.info("DeviceGroup {} stopped", groupId);
	}

	private void onAllTemperatures(RequestAllTemperatures r) {
		getContext().actorOf(DeviceGroupQuery.props(actorToDeviceId, r.requestId, getSender(),
				new FiniteDuration(3, TimeUnit.SECONDS)));
	}

	@Override
	public Receive createReceive() {
		// ... other cases omitted
		return receiveBuilder().match(RequestAllTemperatures.class, this::onAllTemperatures).build();
	}
}