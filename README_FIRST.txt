buidは
カレントディレクトリで
ant
を実行する。

実行は
workspaceに cdしてから行う。
詳細は
workspace下の 実行.txtを参照

PowerShell を用いる場合

管理者権限でPowerShellを起動し以下を実行しておく

Set-ExecutionPolicy RemoteSigned

ついで一般ユーザー権限でPowerShellwを起動
setUp.ps1
のJAVA_HOMEの値を適切にセットしたあと
setUpを実行してJAVA_HOMEの値を変更する

setUpはPowerShellの起動毎必要かも