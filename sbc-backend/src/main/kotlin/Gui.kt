import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

/* Search eBooks Collection sBC
   Gui for starting and stopping the Javalin server 
   Copyright (c) 2019 Burkhardt Renz, THM. All rights reserved.
   The use and distribution terms for this software are covered by the
   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php).
   By using this software in any fashion, you are agreeing to be bound by
   the terms of this license.
 */

class Gui{

    private lateinit var mainFrame: JFrame
    private lateinit var headerLabel: JLabel
    private lateinit var statusLabel: JLabel
    private lateinit var controlPanel: JPanel
    private lateinit var startButton: JButton
    private lateinit var stopButton: JButton
    
    val title = "sBC - Search eBook Collections"
    val rev = "Rev 1.1 2021-10-29"
    
    init {
        prepareGui()
    }
    
    private fun prepareGui() {
        headerLabel = JLabel("This is sBC Server [$rev] on port 7001", JLabel.CENTER)
        statusLabel = JLabel("", JLabel.CENTER).apply {setSize(200, 50)}
        controlPanel = JPanel().apply {layout = FlowLayout() }
        
        mainFrame = JFrame(title).apply {
            setSize(400,200)
            layout = GridLayout(3,1)
            addWindowListener(object : WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    super.windowClosing(e)
                    System.exit(0)
                }
            })
            add(headerLabel)
            add(controlPanel)
            add(statusLabel)
            
            startButton = JButton("Start server").apply {
                actionCommand = "Start"
                addActionListener(ButtonClickListener())
            }
            stopButton = JButton("Stop and exit").apply {
                actionCommand = "Stop"
                addActionListener(ButtonClickListener())
                isEnabled = false
            }
            controlPanel.add(startButton)
            controlPanel.add(stopButton)
            setLocationRelativeTo(null)
            isVisible = true
        }
    }

    private inner class ButtonClickListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            when (e.actionCommand) {
                "Start" -> {
                    startServer()
                    startButton.isEnabled = false
                    stopButton.isEnabled = true
                    statusLabel.text = "Server running."
                }
                else  -> {
                    stopServer()
                    System.exit(0)
                }
            }
        }
    }
    
}