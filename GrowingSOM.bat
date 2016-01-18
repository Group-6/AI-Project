del output\*.*
call "..\..\Program Files (x86)\SOMToolbox\somtoolbox.bat" GrowingSOM MySOM.prop
del MySOM.dwm
call "C:\Program Files\7-Zip\7z.exe" x "output\MySOM.dwm.gz"
call "..\..\Program Files (x86)\SOMToolbox\somtoolbox.bat" SOMViewer -u "output\MySOM.unit.gz" -w "output\MySOM.wgt.gz" --dw "output\MySOM.dwm.gz"