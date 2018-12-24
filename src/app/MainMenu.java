package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import componentsV2.Song;
import componentsV2.User;
import functionsGUIs.AddArtist;
import functionsGUIs.AddPlaylist;
import functionsGUIs.AddSong;
import functionsGUIs.AddSongToPlaylist;
import functionsGUIs.AddUser;
import functionsGUIs.DeleteArtist;
import functionsGUIs.DeletePlaylist;
import functionsGUIs.DeleteSong;
import functionsGUIs.DeleteUser;
import functionsGUIs.FollowPerson;
import functionsGUIs.GetPlaylistDetails;
import functionsGUIs.GetSongAlbum;
import functionsGUIs.ListFollowers;
import functionsGUIs.ViewUserDetails;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.channels.Selector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;
	private JTextPane outputPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//DataPopulation.populateBackend(); //just for testing, you can use to populate if you lose the persistent file
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.pack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Music Player");
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblOutput = new JLabel("Logs:");
		lblOutput.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblOutput.setForeground(Color.WHITE);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(Color.RED);
		btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		JTextPane announcement = new JTextPane();
		announcement.setEditable(false);
		announcement.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		announcement.setForeground(Color.WHITE);
		announcement.setBackground(Color.BLACK);
		announcement.setText("Choose the Required Functionality from the Menu Bar");
		
		JButton btnLoadMusicplayerData = new JButton("Load MusicPlayer Data from File");
		btnLoadMusicplayerData.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnLoadMusicplayerData.setForeground(Color.WHITE);
		btnLoadMusicplayerData.setBackground(new Color(255, 69, 0));
		btnLoadMusicplayerData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser= new JFileChooser();
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    Storer.load(MainMenu.this, selectedFile);
				}
			}
		});
		
		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Storer.store(MainMenu.this);
			}
		});
		btnSaveData.setForeground(Color.WHITE);
		btnSaveData.setBackground(new Color(255, 69, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOutput)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear)
					.addContainerGap(331, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLoadMusicplayerData)
							.addGap(18)
							.addComponent(btnSaveData, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addComponent(announcement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(announcement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadMusicplayerData)
						.addComponent(btnSaveData))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOutput)
						.addComponent(btnClear))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
		);
		
		outputPanel = new JTextPane();
		outputPanel.setEditable(false);
		outputPanel.setBackground(Color.BLACK);
		outputPanel.setForeground(Color.WHITE);
		outputPanel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		outputPanel.setText("*Dialog history will be displayed here*\n");
		scrollPane.setViewportView(outputPanel);
		frame.getContentPane().setLayout(groupLayout);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputPanel.setText("");
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(new Color(255, 69, 0));
		frame.setJMenuBar(menuBar);
		
		JMenu mnMainMenu = new JMenu("Main Menu");
		mnMainMenu.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.setBackground(new Color(255, 69, 0));
		mnMainMenu.setForeground(Color.BLACK);
		menuBar.add(mnMainMenu);
		
		JMenuItem mntmAddUser = new JMenuItem("Add User");
		mntmAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser addUser = new AddUser(MainMenu.this);
				addUser.pack();
				addUser.setVisible(true);
			}
		});
		mntmAddUser.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddUser.setForeground(Color.BLACK);
		mntmAddUser.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddUser);
		
		JMenuItem mntmDeleteUserBy = new JMenuItem("Delete User");
		mntmDeleteUserBy.setForeground(Color.BLACK);
		mntmDeleteUserBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteUser deleteUser = new DeleteUser(MainMenu.this);
				deleteUser.pack();
				deleteUser.setVisible(true);
			}
		});
		mntmDeleteUserBy.setBackground(new Color(255, 69, 0));
		mntmDeleteUserBy.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeleteUserBy);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View User Details");
		mntmNewMenuItem.setForeground(Color.BLACK);
		mntmNewMenuItem.setBackground(new Color(255, 69, 0));
		mntmNewMenuItem.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserDetails viewUserDetails = new ViewUserDetails(MainMenu.this);
				viewUserDetails.pack();
				viewUserDetails.setVisible(true);
						
			}
		});
		mnMainMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmAddSong = new JMenuItem("Add Song");
		mntmAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSong addSong = new AddSong(MainMenu.this);
				addSong.pack();
				addSong.setVisible(true);
			}
		});
		mntmAddSong.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddSong.setForeground(Color.BLACK);
		mntmAddSong.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddSong);
		
		JMenuItem mntmDeleteSong = new JMenuItem("Delete Song");
		mntmDeleteSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteSong deleteSong = new DeleteSong(MainMenu.this);
				deleteSong.pack();
				deleteSong.setVisible(true);
			}
		});
		mntmDeleteSong.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmDeleteSong.setBackground(new Color(255, 69, 0));
		mntmDeleteSong.setForeground(Color.BLACK);
		mnMainMenu.add(mntmDeleteSong);
		
		JMenuItem mntmGetSongAlbum = new JMenuItem("Get Song Album");
		mntmGetSongAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetSongAlbum getSongAlbum = new GetSongAlbum(MainMenu.this);
				getSongAlbum.pack();
				getSongAlbum.setVisible(true);
			}
		});
		mntmGetSongAlbum.setBackground(new Color(255, 69, 0));
		mntmGetSongAlbum.setForeground(Color.BLACK);
		mntmGetSongAlbum.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmGetSongAlbum);
		
		JMenuItem mntmAddPlaylist = new JMenuItem("Add Playlist");
		mntmAddPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlaylist addPlaylist = new AddPlaylist(MainMenu.this);
				addPlaylist.pack();
				addPlaylist.setVisible(true);
			}
		});
		mntmAddPlaylist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddPlaylist.setForeground(Color.BLACK);
		mntmAddPlaylist.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddPlaylist);
		
		JMenuItem mntmAddSongTo = new JMenuItem("Add Song to Playlist");
		mntmAddSongTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSongToPlaylist addSongToPlaylist = new AddSongToPlaylist(MainMenu.this);
				addSongToPlaylist.pack();
				addSongToPlaylist.setVisible(true);
			}
		});
		mntmAddSongTo.setForeground(Color.BLACK);
		mntmAddSongTo.setBackground(new Color(255, 69, 0));
		mntmAddSongTo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmAddSongTo);
		
		JMenuItem mntmGetPlaylistDetails = new JMenuItem("Get Playlist Details");
		mntmGetPlaylistDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetPlaylistDetails getPlaylistDetails = new GetPlaylistDetails(MainMenu.this);
				getPlaylistDetails.pack();
				getPlaylistDetails.setVisible(true);
			}
		});
		mntmGetPlaylistDetails.setBackground(new Color(255, 69, 0));
		mntmGetPlaylistDetails.setForeground(Color.BLACK);
		mntmGetPlaylistDetails.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmGetPlaylistDetails);
		
		JMenuItem mntmDeletePlaylistFor = new JMenuItem("Delete Playlist for User");
		mntmDeletePlaylistFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePlaylist deletePlaylist = new DeletePlaylist(MainMenu.this);
				deletePlaylist.pack();
				deletePlaylist.setVisible(true);
			}
		});
		mntmDeletePlaylistFor.setForeground(Color.BLACK);
		mntmDeletePlaylistFor.setBackground(new Color(255, 69, 0));
		mntmDeletePlaylistFor.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeletePlaylistFor);
		
		JMenuItem mntmDisplayAllSongs = new JMenuItem("Display All Songs");
		mntmDisplayAllSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnTitles = new String[] {"ID", "Name", "Genre", "Release Date", "Album", "Artist"};
				String[][] tableContent = new String[Backend.songs.size()][6];
				for(int i = 0; i<Backend.songs.size(); i++)
				{
					Song temp = Backend.songs.get(i);
					tableContent[i] = new String[] {Integer.toString(temp.getId()), temp.getName(), temp.getGenre(), temp.getReleaseDate().toString(), temp.getAlbums().get(0).getName(), temp.getArtist().getName()};
				}
				outputTable oTable = new outputTable("All Songs Details", columnTitles, tableContent);
				oTable.pack();
				oTable.setVisible(true);
			}
		});
		mntmDisplayAllSongs.setForeground(Color.BLACK);
		mntmDisplayAllSongs.setBackground(new Color(255, 69, 0));
		mntmDisplayAllSongs.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDisplayAllSongs);
		
		JMenuItem mntmDisplayAllUsers = new JMenuItem("Display All Users");
		mntmDisplayAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnTitles = new String[] {"Real Name", "DOB", "Username", "Subscription Type", "Subscription Expiry"};
				String[][] tableContent = new String[Backend.users.size()][5];
				for(int i = 0; i<Backend.users.size(); i++)
				{
					User temp = Backend.users.get(i);
					tableContent[i] = new String[] {temp.getName(), temp.getDOB().toString(), temp.getUserName(), temp.getSubscription().getSubType(), temp.getSubscription().getExpiryDate().toString()};
				}
				outputTable oTable = new outputTable("All Users Details", columnTitles, tableContent);
				oTable.pack();
				oTable.setVisible(true);
			}
		});
		mntmDisplayAllUsers.setForeground(Color.BLACK);
		mntmDisplayAllUsers.setBackground(new Color(255, 69, 0));
		mntmDisplayAllUsers.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDisplayAllUsers);
		
		JMenuItem mntmAddArtist = new JMenuItem("Add Artist");
		mntmAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddArtist addArtist = new AddArtist(MainMenu.this);
				addArtist.pack();
				addArtist.setVisible(true);
			}
		});
		mntmAddArtist.setForeground(Color.BLACK);
		mntmAddArtist.setBackground(new Color(255, 69, 0));
		mntmAddArtist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmAddArtist);
		
		JMenuItem mntmDeleteArtist = new JMenuItem("Delete Artist");
		mntmDeleteArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteArtist deleteArtist = new DeleteArtist(MainMenu.this);
				deleteArtist.pack();
				deleteArtist.setVisible(true);
			}
		});
		mntmDeleteArtist.setForeground(Color.BLACK);
		mntmDeleteArtist.setBackground(new Color(255, 69, 0));
		mntmDeleteArtist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeleteArtist);
		
		JMenuItem mntmFollowPerson = new JMenuItem("Follow Person");
		mntmFollowPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FollowPerson followPerson = new FollowPerson(MainMenu.this);
				followPerson.pack();
				followPerson.setVisible(true);
			}
		});
		mntmFollowPerson.setForeground(Color.BLACK);
		mntmFollowPerson.setBackground(new Color(255, 69, 0));
		mntmFollowPerson.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmFollowPerson);
		
		JMenuItem mntmListFollowers = new JMenuItem("List Followers");
		mntmListFollowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFollowers listFollowers = new ListFollowers(MainMenu.this);
				listFollowers.pack();
				listFollowers.setVisible(true);
			}
		});
		mntmListFollowers.setForeground(Color.BLACK);
		mntmListFollowers.setBackground(new Color(255, 69, 0));
		mntmListFollowers.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmListFollowers);
	}
	
	public void logMessage(String message)
	{
		outputPanel.setText(outputPanel.getText() + "-> " + message + "\n");
	}
}
