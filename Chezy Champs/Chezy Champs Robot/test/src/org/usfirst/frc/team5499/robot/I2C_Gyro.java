package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.I2C;

public class I2C_Gyro {
	private int address;
	private I2C.Port port;
	private byte[] dataBuffer = new byte[2];
	//private ByteBuffer compBuffer = ByteBuffer.wrap(dataBuffer);
	private Ranges currRange;
	
	private I2C gyro = new I2C(port, address);
	
	/**
	 * Create a new gyro to use with the I2C port
	 * @param port
	 * 			The port on the roboRio to which the gyro is connected 
	 * @param address
	 * 			The I2C address of the gyro
	 */
	public I2C_Gyro(I2C.Port port, int address){
		this.address = address; //0x6B
		this.port = port; //kOnBoard
	}
	
	/**
	 * Addresses of the registers on the gyro
	 *
	 */
	private enum RegisterAddresses{
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
		L3GD20_REGISTER_INT1_SRC           	(0x31),   //            r 
		L3GD20_REGISTER_TSH_XH              (0x32),   // 00000000   rw 
		L3GD20_REGISTER_TSH_XL              (0x33),   // 00000000   rw 
		L3GD20_REGISTER_TSH_YH              (0x34),   // 00000000   rw 
		L3GD20_REGISTER_TSH_YL              (0x35),   // 00000000   rw 
		L3GD20_REGISTER_TSH_ZH              (0x36),   // 00000000   rw 
		L3GD20_REGISTER_TSH_ZL              (0x37),   // 00000000   rw 
		L3GD20_REGISTER_INT1_DURATION       (0x38);   // 00000000   rw 
		
		private final int address;
		private RegisterAddresses(int address){
			this.address = address;
		}
		private int getValue(){
			return address;
		}
	}
	
	/**
	 * List of sensitivities of the gyro 
	 *
	 */
	public enum Ranges{
		L3DS20_RANGE_250DPS 	(0x00), 
		L3DS20_RANGE_500DPS		(0x10), 
		L3DS20_RANGE_2000DPS 	(0x20);
		
		private final int range;
		private Ranges(int range){
			this.range = range;
		}
		private int getValue(){
			return range;
		}
	}
	
	/**
	 * Enable the gyro (switch to normal mode and enable all 3 axes)
	 */
	public void enable(){
		gyro.write(RegisterAddresses.L3GD20_REGISTER_CTRL_REG1.getValue(), 0x0f);
	}
	
	/**
	 * TODO is this in degs?
	 * @return
	 * 		The z reading of the gyro, in an int, compensated for the range
	 */
	public int getZ(){
		gyro.write(RegisterAddresses.L3GD20_REGISTER_OUT_Z_L.getValue(), 0x80);
		gyro.read(RegisterAddresses.L3GD20_REGISTER_OUT_Z_L.getValue(), 2, dataBuffer);
		byte zlo = dataBuffer[0];
		byte zhi = dataBuffer[1];
		int z = (int)(zlo | (zhi << 8));
		//compBuffer.order(ByteOrder.BIG_ENDIAN);
		//int z = compBuffer.getInt();
		int zCompens = z * currRange.getValue();
		return zCompens;
	}
	
	/**
	 * Set the sensitivity of the gyro
	 * @param range
	 * 			The desired sensitivity of the gyro. Use the Ranges Enum in the I2C_Gyro class
	 */
	public void setRange(Ranges range){
		gyro.write(RegisterAddresses.L3GD20_REGISTER_CTRL_REG4.getValue(), range.getValue());
		currRange = range;
	}
}
