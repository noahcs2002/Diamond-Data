$scriptDir = $PSScriptRoot

$script1 = Join-Path -Path $scriptDir -ChildPath "app.ps1"
$script2 = Join-Path -Path $scriptDir -ChildPath "api.ps1"

$process1 = Start-Process powershell.exe -ArgumentList "-File `"$script1`"" -PassThru -WindowStyle Hidden
$process2 = Start-Process powershell.exe -ArgumentList "-File `"$script2`"" -PassThru -WindowStyle Hidden

Write-Host "API and Application are now starting up."
Write-Host "Use ctrl+c to terminate this program and stop the application."

while ($true) {
    Start-Sleep -Seconds 5

    $runningProcesses = @($process1, $process2) | Where-Object { !$_.HasExited }

    if ($runningProcesses.Count -eq 0) {
        break  
    }
}