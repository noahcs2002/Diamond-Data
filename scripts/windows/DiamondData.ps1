Start-Job -Name "AppJob" -ScriptBlock { & .\app.ps1 } 
Start-Job -Name "ApiJob" -ScriptBlock { & .\api.ps1 } 
Wait-Job -Name "AppJob", "ApiJob"
