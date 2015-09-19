package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * 
 * Based on the L3GD20
 *
 */
public class I2C_Gyro {
	private I2C.Port port;
	private int address;
	
	
	private enum registerAddresses{		
		L3GD20_REGISTER_WHO_AM_I            (0x0F),   // 11010100   r 
		L3GD20_REGISTER_CTRL_REG1           (0x20),   // 00000111   rw 
		L3GD20_REGISTER_CTRL_REG2           (0x21),   // 00000000   rw 
		L3GD20_REGISTER_CTRL_REG3           (0x22),   // 00000000   rw 
		L3GD20_REGISTER_CTRL_REG4           (0x23),   // 00000000   rw 
		L3GD20_REGISTER_CTRL_REG5           (0x24),   // 00000000   rw 
		L3GD20_REGISTER_REFERENCE           (0x25),   // 00000000   rw 
		L3GD20_REGISTER_OUT_TEMP            (0x26),   //            r 
		L3GD20_REGISTER_STATUS_REG          (0x27),   //            r 
		L3GD20_REGISTER_OUT_X_L             (0x28),   //            r 
		L3GD20_REGISTER_OUT_X_H             (0x29),   //            r 
		L3GD20_REGISTER_OUT_Y_L             (0x2A),   //            r 
		L3GD20_REGISTER_OUT_Y_H             (0x2B),   //            r 
		L3GD20_REGISTER_OUT_Z_L             (0x2C),   //            r 
		L3GD20_REGISTER_OUT_Z_H             (0x2D),   //            r 
		L3GD20_REGISTER_FIFO_CTRL_REG       (0x2E),   // 00000000   rw 
		L3GD20_REGISTER_FIFO_SRC_REG        (0x2F),   //            r 
		L3GD20_REGISTER_INT1_CFG            (0x30),   // 00000000   rw 
		L3GD20_REGISTER_INT1_SRC            (0x31),   //            r 
		L3GD20_REGISTER_TSH_XH              (0x32),   // 00000000   rw 
		L3GD20_REGISTER_TSH_XL              (0x33),   // 00000000   rw 
		L3GD20_REGISTER_TSH_YH              (0x34),   // 00000000   rw 
		L3GD20_REGISTER_TSH_YL              (0x35),   // 00000000   rw 
		L3GD20_REGISTER_TSH_ZH              (0x36),   // 00000000   rw 
		L3GD20_REGISTER_TSH_ZL              (0x37),   // 00000000   rw 
		L3GD20_REGISTER_INT1_DURATION       (0x38);   // 00000000   rw
		
		private final int address;
		private registerAddresses(int address) {
			this.address = address;
		}

		public int getAddress() {
			return address;
		}
	}
	/*private enum sensitivity(){
		L3DS20_RANGE_250DPS		(0.00875F), 
		L3DS20_RANGE_500DPS		(0.0175F), 
		L3DS20_RANGE_2000DPS	(0.070F);
		
		private final float sensitivity;
		private sensitivity(float sensitivity){
			this.sensitivity = sensitivity;
		}
		
		public float getSensitivity(){
			return sensitivity;
		}

	}*/
	
	private I2C gyro = new I2C(port, address);
	
	/**
	 * Create a new gyro for use with the I2C port on the roboRio
	 * @param port
	 * 			Which I2C port is the gyro plugged into. If none specified, kOnBoard with be used
	 * @param address
	 * 			The I2C address of your gyro. If not specified, 0x6B will be used
	 */
	public I2C_Gyro(I2C.Port port, int address){
		this.port = port;
		this.address = address;
		if(gyro.addressOnly()){
			System.out.println("Wrong address!");
		}
	}
	/**
	 * Create a new gyro for use with the I2C port on the roboRio
	 * @param port
	 * 			Which I2C port is the gyro plugged into. If none specified, kOnBoard with be used
	 */
	public I2C_Gyro(I2C.Port port){
		this.port = port;
		this.address = 0x6B;
	}
	/**
	 * Create a new gyro for use with the I2C port on the roboRio
	 * @param address
	 * 			The I2C address of your gyro. If not specified, 0x6B will be used
	 */
	public I2C_Gyro(int address){
		this.port = I2C.Port.kOnboard;
		this.address = address;
	}
	/**
	 * Create a new gyro for use with the I2C port on the roboRio
	 */
	public I2C_Gyro(){
		this.port = I2C.Port.kOnboard;
		this.address = 0x6B;
	}
	
	private void testAddress(){
		if(gyro.addressOnly()){
			System.out.println("Wrong address!");
		}
	}
	
	private double getX(){
		double x = 0;
		return x;
	}
	
	private double getY(){
		double y = 0;
		return y;
	}
	
	private double getZ(){
		double z = 0;
		return z;
	}
	private double[] getXYZ(){
		double[] xyz = {0,0,0};
		return xyz;
	}
}
