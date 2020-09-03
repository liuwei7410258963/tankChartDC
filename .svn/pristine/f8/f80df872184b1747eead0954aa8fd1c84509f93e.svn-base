package com.oket.tank4station;

import com.oket.dispenser.NozzleState;
import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import org.junit.jupiter.api.Test;

public class AbstractTankSessionTest {

	@Test
	public void nozzleStateChanged() {
		NozzleState nozzleState = new NozzleState(1,NozzleState.State.FUELLING);


		NozzleState nozzleState2 = new NozzleState(2,NozzleState.State.SUSPENDED_FUELLING);


		AbstractTankSession tankSession = new TankStationSession(1);
		tankSession.nozzleStateChanged(nozzleState);
		tankSession.nozzleStateChanged(nozzleState2);
		byte[] bytes = MathExtend.longToBytes(tankSession.getNozzleState().longValue(), 8);
		System.out.println("1:" + StringExUtils.byteArrayToHexString(bytes));


		nozzleState.setNozzleNo(1);
		nozzleState.setCurrentState(NozzleState.State.IDLE);
		tankSession.nozzleStateChanged(nozzleState);


		bytes = MathExtend.longToBytes(tankSession.getNozzleState().longValue(), 8);
		System.out.println("2:" + StringExUtils.byteArrayToHexString(bytes));

		nozzleState2.setNozzleNo(2);
		nozzleState2.setCurrentState(NozzleState.State.CLOSE);
		tankSession.nozzleStateChanged(nozzleState2);

		bytes = MathExtend.longToBytes(tankSession.getNozzleState().longValue(), 8);
		System.out.println("3:" + StringExUtils.byteArrayToHexString(bytes));

	}


	@Test
	private void test1() {
		System.out.println(0<<2);
	}
	public static void main(String[] args){
		long v = 0xFF << 4;
		v = ~v;

		System.out.println("3:" + StringExUtils.byteArrayToHexString( MathExtend.longToBytes(v, 8)));
		System.out.println("4:" + StringExUtils.byteArrayToHexString( MathExtend.longToBytes(0xFF, 8)));
		System.out.println("4:" + StringExUtils.byteArrayToHexString( MathExtend.longToBytes(0xFF<<0, 8)));
	}
}