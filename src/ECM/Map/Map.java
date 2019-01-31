package ECM.Map;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.painter.Painter;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.*;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import ECM.Insertion;

public class Map
{
	static CompoundPainter<JXMapViewer> painter;
	
	public static JXMapKit jXMapKit;
	public static JXMapViewer mapViewer;

	public static int zoom;
	public static double lat, lon;

	public static JPanel newMap()
	{
		jXMapKit = new JXMapKit();
		mapViewer = new JXMapViewer();

        TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);

		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));

		GeoPosition rabat = new GeoPosition(33.97, -6.84);

		// --------------------------------------------------------------------------------------------------------------------
		mapViewer.setZoom(6);
		mapViewer.setAddressLocation(rabat);

		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

		mapViewer.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if ( e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3)
				{
					java.awt.Point p = e.getPoint();
					GeoPosition geo = mapViewer.convertPointToGeoPosition(p);
					lat = geo.getLatitude();
					lon = geo.getLongitude();
					if(Insertion.currentFrame)
					{
						double[] info= {lat,lon,mapViewer.getZoom()-18};
						goTo(info);
						
						Insertion.address.setText(Geolocalisation.getAddress(lat,lon));
						Insertion.canInsert=true;
						
						Insertion.latitude=lat;
						Insertion.longitude=lon;
						
						Insertion.BInsert.setBackground(new Color(0, 200, 200));
					}
				}
			}
		});
		return mapViewer;
	}

	public static void goTo(double[] info)
	{
		int zoom = 18 - (int) (info[2]);
		Set<Waypoint> waypoints = new HashSet<Waypoint>(Arrays.asList(new DefaultWaypoint(new GeoPosition(info[0], info[1]))));
		
		mapViewer.setZoom(zoom);
		mapViewer.setAddressLocation(new GeoPosition(info[0], info[1]));
		
		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(waypoints);
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(waypointPainter);
        painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);
	        
		updateWindowTitle(mapViewer);
	}

	protected static void updateWindowTitle(JXMapViewer mapViewer)
	{
		lat = mapViewer.getCenterPosition().getLatitude();
		lon = mapViewer.getCenterPosition().getLongitude();
		zoom = mapViewer.getZoom();
	}
	
	public static void deleteWaypoint()
	{
		painter=null;
        mapViewer.setOverlayPainter(painter);
	}
}
