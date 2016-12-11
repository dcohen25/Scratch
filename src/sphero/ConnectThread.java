package sphero;
import java.util.Collection;

import se.nicklasgavelin.bluetooth.Bluetooth;
import se.nicklasgavelin.bluetooth.Bluetooth.EVENT;
import se.nicklasgavelin.bluetooth.BluetoothDevice;
import se.nicklasgavelin.bluetooth.BluetoothDiscoveryListener;
import se.nicklasgavelin.sphero.Robot;
import se.nicklasgavelin.sphero.command.FrontLEDCommand;
import se.nicklasgavelin.sphero.command.RGBLEDCommand;
import se.nicklasgavelin.sphero.exception.InvalidRobotAddressException;
import se.nicklasgavelin.sphero.exception.RobotBluetoothException;

/**
	 * Handles the detection of new devices and listens on our robots for
	 * responses and events
	 */
	public class ConnectThread implements BluetoothDiscoveryListener
	{
		// Internal storage
		private Bluetooth bt;
		private boolean stop = false;

		/**
		 * Create a connect thread
		 */
		public ConnectThread()
		{
			
		}

		public void run()
		{
			try
			{

				// Uncomment the code below and comment out the bt.discover() line above
				// to
				// connect directly to a given Sphero

				// // ## START UNCOMMENT ##
				// final String bluetoothAddress = "0006664438B8";
				// BluetoothDevice btd = new BluetoothDevice( bt, "btspp://" +
				// bluetoothAddress + ":1;authenticate=true;encrypt=false;master=false" );
				//
				// // Create the robot from the bluetooth device
				// Robot r = new Robot( btd );
				//
				// // Try to connect to the robot
				// if ( r.connect() )
				// {
				// // Add ourselves as listeners
				// r.addListener( this );
				//
				// // Send a rgb transition command macro
				// r.rgbTransition( 255, 0, 0, 0, 255, 255, 50 );
				//
				// // Send a direct command
				// r.sendCommand( new FrontLEDCommand( 1F ) );
				// }
				// // ## END UNCOMMENT ##

				// Run forever, euheuheuh!
			}
			catch( Exception e )
			{
				// Failure in searching for devices for some reason.
				e.printStackTrace();
			}
		}

		/*
		 * *************************************
		 * BLUETOOTH DISCOVERY STUFF
		 */

		/**
		 * Called when the device search is completed with detected devices
		 * 
		 * @param devices The devices detected
		 */
		@Override
		public void deviceSearchCompleted( Collection<BluetoothDevice> devices )
		{
			// Device search is completed
			System.out.println( "Completed device discovery" );

			// Try and see if we can find any Spheros in the found devices
			for( BluetoothDevice d : devices )
			{
				System.out.println( "Found device " + d.getAddress() );

				// Check if the Bluetooth device is a Sphero device or not
				if( Robot.isValidDevice( d ) )
				{
					System.out.println( "Found robot " + d.getAddress() );

					// We got a valid device (Sphero device), connect to it and
					// have some fun with colors.
					try
					{
						// Create our robot from the Bluetooth device that we got
						Robot r = new Robot( d );

						

						// Check if we can connect
						if( r.connect() )
						{
							System.out.println( "Connected to " + d.getName() + " : " + d.getAddress() );

						    r.sendCommand( new RGBLEDCommand( 255, 0, 0 ) );

//							r.rgbTransition( 255, 0, 0, 0, 255, 255, 50 );
//
//							// Send direct command
//							r.sendCommand( new FrontLEDCommand( 1 ) );
						}
						else
							System.err.println( "Failed to connect to robot" );
					}
					catch( InvalidRobotAddressException ex )
					{
						ex.printStackTrace();
					}
					catch( RobotBluetoothException ex )
					{
						ex.printStackTrace();
					}
				}
			}
		}

		@Override
		public void deviceDiscovered(BluetoothDevice arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deviceSearchFailed(EVENT arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deviceSearchStarted() {
			// TODO Auto-generated method stub
			
		}
	}