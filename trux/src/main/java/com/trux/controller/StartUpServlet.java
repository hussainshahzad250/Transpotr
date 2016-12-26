package com.trux.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.trux.mail.MailThread;


@SuppressWarnings("serial")
public class StartUpServlet extends HttpServlet
{
 
    public void init() throws ServletException
    {
          System.out.println("----------");
          System.out.println("---------- StartUpServlet Servlet Initialized successfully ----------");
          MailThread mailThread = MailThread.getInstance();
		  mailThread.start();
          System.out.println("----------");
    }
}