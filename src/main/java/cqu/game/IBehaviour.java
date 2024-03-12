/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cqu.game;

/**
 *
 * @author 12233612
 */
public interface IBehaviour  {
     
    public Node emptyTree();
    public boolean processNonLeafNode(Node n);
    public boolean processLeafNode(Node n);
}
