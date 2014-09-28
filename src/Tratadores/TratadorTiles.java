package Tratadores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Interface.FramePrincipal;
import Interface.PainelSenha;

public class TratadorTiles implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {

		FramePrincipal fp = FramePrincipal.getInstance();
		PainelSenha ps = (PainelSenha) arg0.getComponent().getParent();
		String novaTela = "imagens/telas/Senha2.png";

		if (arg0.getComponent().getName().equals("Tile1"))
		{
			fp.user.setSenha (fp.user.getSenha()+ps.getPasswordBuilder().substring(0, 2));	
			ps.removeTiles();
			if(fp.user.getSenha().length()<12) {
				ps.resetPasswordBuilder();
				ps.createTiles(ps.getPasswordBuilder());
			} else {
				try
				{
					ps.setI(ImageIO.read(new File(novaTela)));
					ps.getGo().setVisible(true);
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
		}

		else if (arg0.getComponent().getName().equals("Tile2"))
		{
			fp.user.setSenha(fp.user.getSenha()+ps.getPasswordBuilder().substring(2, 4));	
			ps.removeTiles();
			if(fp.user.getSenha().length()<12) {
				ps.resetPasswordBuilder();
				ps.createTiles(ps.getPasswordBuilder());
			} else {
				try
				{
					ps.setI(ImageIO.read(new File(novaTela)));
					ps.getGo().setVisible(true);
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
		}

		else if (arg0.getComponent().getName().equals("Tile3"))
		{
			fp.user.setSenha(fp.user.getSenha()+ps.getPasswordBuilder().substring(4, 6));	
			ps.removeTiles();
			if(fp.user.getSenha().length()<12) {
				ps.resetPasswordBuilder();
				ps.createTiles(ps.getPasswordBuilder());
			} else {
				try
				{
					ps.setI(ImageIO.read(new File(novaTela)));
					ps.getGo().setVisible(true);
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
		}

		else if (arg0.getComponent().getName().equals("Tile4"))
		{
			fp.user.setSenha(fp.user.getSenha()+ps.getPasswordBuilder().substring(6, 8));	
			ps.removeTiles();
			if(fp.user.getSenha().length()<12) {
				ps.resetPasswordBuilder();
				ps.createTiles(ps.getPasswordBuilder());
			} else {
				try
				{
					ps.setI(ImageIO.read(new File(novaTela)));
					ps.getGo().setVisible(true);
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
		}

		else if (arg0.getComponent().getName().equals("Tile5"))
		{
			fp.user.setSenha(fp.user.getSenha()+ps.getPasswordBuilder().substring(8, 10));	
			ps.removeTiles();
			if(fp.user.getSenha().length()<12) {
				ps.resetPasswordBuilder();
				ps.createTiles(ps.getPasswordBuilder());
				} else {
				try
				{
					ps.setI(ImageIO.read(new File(novaTela)));
					ps.getGo().setVisible(true);
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
		}
		
		Integer novo = (fp.user.getSenha().length())/2;
		ps.renameJF(novo.toString());
		ps.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {


	}
}
